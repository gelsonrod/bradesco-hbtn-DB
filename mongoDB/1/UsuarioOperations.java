import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Arrays;

public class UsuarioOperations {

    private static void listar(MongoCollection<Document> col) {
        System.out.println("---- Listagem de usuários ----");
        for (Document d : col.find()) {
            System.out.println(Usuario.fromDocument(d));
        }
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        MongoDBConnection conn = new MongoDBConnection();
        try {
            MongoDatabase db = conn.getDatabase();
            if (db == null) {
                System.err.println("Sem conexão com o banco.");
                return;
            }

            MongoCollection<Document> usuarios = db.getCollection("usuarios");

            // Limpa registros anteriores apenas desses nomes (idempotente para testes)
            usuarios.deleteMany(Filters.in("nome", Arrays.asList("Alice", "Bob", "Charlie")));

            // 1) Inserir 3 registros (insertMany)
            usuarios.insertMany(Arrays.asList(
                new Usuario("Alice", 25).toDocument(),
                new Usuario("Bob", 30).toDocument(),
                new Usuario("Charlie", 35).toDocument()
            ));

            // 2) Consultar
            listar(usuarios);

            // 3) Alterar idade de Bob para 32 (updateOne)
            usuarios.updateOne(Filters.eq("nome", "Bob"), Updates.set("idade", 32));

            // 4) Consultar
            listar(usuarios);

            // 5) Apagar registro de Charlie (deleteOne)
            usuarios.deleteOne(Filters.eq("nome", "Charlie"));

            // 6) Consultar
            listar(usuarios);

        } finally {
            conn.closeConnection();
        }
    }
}
