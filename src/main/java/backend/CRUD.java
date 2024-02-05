/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
//IMPORTS
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author dell
 */
public class CRUD {
    private final MongoCollection<Document> coleccionDatos ;
    private int indiceDatos;
    private int obtenerUltimoIndice(MongoCollection<Document> coleccion) {
    Document ultimoDocumento = coleccion.find().sort(new Document("indice", -1)).limit(1).first();
    
    if (ultimoDocumento != null) {
        return ultimoDocumento.getInteger("indice", 0);
    } else {
        return 0;
    }
    }
            
    // CONSTRUCTOR
    public CRUD(MongoDatabase database) {
        if (database != null) {
            coleccionDatos = database.getCollection("CaidaLibre");
            indiceDatos = obtenerUltimoIndice(coleccionDatos) + 1;
        } else {
            throw new IllegalArgumentException("La base de datos no puede ser nula");
        }
    }
    
    // SAVE
    public void GuardarDB(double altura, double velocidad, double tiempo) {
        try {
        indiceDatos = obtenerUltimoIndice(coleccionDatos) + 1; // Incrementar el índice cada vez que se guarda
        Document documentoCaidaLibre = new Document("tipo", "Caida Libre")
                .append("indice", indiceDatos)
                .append("altura", altura)
                .append("velocidad", velocidad)
                .append("tiempo", tiempo);
        coleccionDatos.insertOne(documentoCaidaLibre);
        System.out.println("Datos insertados en la base de datos");
    } catch (MongoException e) {
        System.err.println("Error al almacenar en la base de datos: " + e.getMessage());
    }
    }

    // GET
    public List<Caida_Libre> obtenerListaCaidaLibre(){
    List<Caida_Libre> listaCaidaLibre = new ArrayList<>();
    try {
        if (coleccionDatos != null) {
            FindIterable<Document> cursor = coleccionDatos.find();
            MongoCursor<Document> iterator = cursor.iterator();
            while (iterator.hasNext()) {
                Document documentoCaidaLibre = iterator.next();
                
                double altura  = documentoCaidaLibre.getDouble("altura");
                double velocidad  = documentoCaidaLibre.getDouble("velocidad");
                double tiempo  = documentoCaidaLibre.getDouble("tiempo");
                int indice = documentoCaidaLibre.getInteger("indice");
                
                Caida_Libre caidaLibre = new Caida_Libre(altura, tiempo);
                caidaLibre.setAltura(altura);
                caidaLibre.setTiempo(tiempo);
                caidaLibre.setVelocidadFinal(velocidad);
                caidaLibre.setIndice(indice);
                listaCaidaLibre.add(caidaLibre);
            }
            iterator.close();
        } else {
            System.err.println("La colección de datos es nula");
        }
    } catch (MongoException e) {
        System.err.println("Error al obtener la lista de datos: " + e.getMessage());
    }
        return listaCaidaLibre;
    }
    
    // DELETE
    public void VaciarDB(){
     try {
         coleccionDatos.deleteMany(new Document());
         System.out.println("Colección de datos eliminada en la base de datos");
     } catch (MongoException e) {
         System.err.println("Error al eliminar la colección de datos: " + e.getMessage());
     }
    }
}
