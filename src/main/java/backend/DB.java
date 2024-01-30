/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
//IMPORTS
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author dell
 */
public class DB {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public boolean establecerConexion() {
        try {
             Dotenv dotenv = Dotenv.configure()
                        .directory("src/main/assets")
                        .filename(".env") // instead of '.env', use 'env'
                        .load();
            String dbUsername = dotenv.get("DB_USERNAME");
            String dbPassword = dotenv.get("DB_PASSWORD");
            String dbName = dotenv.get("DB_NAME");
            
            // Intentar la conexión a MongoDB
            MongoClientURI uri = new MongoClientURI(getMongoDBUri(dbUsername, dbPassword, dbName));
            mongoClient = new MongoClient(uri);

            // Acceso a la base de datos
            database = mongoClient.getDatabase("dbExamen");
            System.out.println("Conexión exitosa a la base de datos");
            return true;
        } catch (MongoException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void cerrarConexion() {
        try {
            if (mongoClient != null) {
                mongoClient.close();
                System.out.println("Conexión cerrada");
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    private String getMongoDBUri(String username, String password, String dbName) {
    return "mongodb+srv://" + username + ":" + password + "@cluster.bu7zten.mongodb.net/" + dbName
            + "?retryWrites=true&w=majority";
    }
}