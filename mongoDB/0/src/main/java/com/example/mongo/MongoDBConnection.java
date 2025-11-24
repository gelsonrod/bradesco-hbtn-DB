package com.example.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MongoDBConnection {

    // ========= CONFIG =========
    // Ajuste para os dados do seu cluster (ou use variáveis de ambiente MONGO_*)
    private static final String USERNAME =
            System.getenv().getOrDefault("MONGO_USERNAME", "aluno");
    private static final String PASSWORD_RAW =
            System.getenv().getOrDefault("MONGO_PASSWORD", "BN10UNhMxMDZUgEh");
    private static final String CLUSTER_URL  =
            System.getenv().getOrDefault("MONGO_CLUSTER_URL", "cluster0.brbyr.mongodb.net");
    // Nome do banco (database) que você quer acessar
    private static final String DATABASE_NAME =
            System.getenv().getOrDefault("MONGO_DB", "Cluster0");
    // Apenas um identificador do aplicativo (qualquer string)
    private static final String APP_NAME =
            System.getenv().getOrDefault("MONGO_APP_NAME", "JavaMongoDemo");
    // ==========================

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {
        try {
            // Encode da senha para evitar erro com caracteres especiais
            String PASSWORD = URLEncoder.encode(PASSWORD_RAW, StandardCharsets.UTF_8.name());

            String connectionString = String.format(
                "mongodb+srv://%s:%s@%s/?retryWrites=true&w=majority&appName=%s",
                USERNAME, PASSWORD, CLUSTER_URL, APP_NAME
            );

            ConnectionString connString = new ConnectionString(connectionString);
            ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .serverApi(serverApi)
                    .build();

            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(DATABASE_NAME);

            System.out.println("Conexão estabelecida com sucesso ao MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexão encerrada com sucesso.");
        }
    }

    public static void main(String[] args) {
        MongoDBConnection connection = new MongoDBConnection();

        if (connection.getDatabase() != null) {
            System.out.println("Banco de dados: " + connection.getDatabase().getName());
            System.out.println("Coleções existentes:");
            for (String name : connection.getDatabase().listCollectionNames()) {
                System.out.println(" - " + name);
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        connection.closeConnection();
    }
}
