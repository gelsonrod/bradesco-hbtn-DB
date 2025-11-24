import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    // Variáveis de configuração (ajuste conforme seu Atlas)
    private static final String USERNAME = "aluno";
    private static final String PASSWORD = "BN10UNhMxMDZUgEh";
    private static final String CLUSTER_URL = "cluster0.brbyr.mongodb.net"; // Substitua se necessário
    private static final String DATABASE_NAME = "Cluster0"; // Nome do database

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {
        try {
            String connectionString =
                "mongodb+srv://" + USERNAME + ":" + PASSWORD + "@" + CLUSTER_URL +
                "/?retryWrites=true&w=majority&appName=" + DATABASE_NAME;

            ConnectionString connString = new ConnectionString(connectionString);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .build();

            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(DATABASE_NAME);

            System.out.println("Conexão estabelecida com sucesso ao MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public MongoDatabase getDatabase() { return database; }

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
        }
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        connection.closeConnection();
    }
}
