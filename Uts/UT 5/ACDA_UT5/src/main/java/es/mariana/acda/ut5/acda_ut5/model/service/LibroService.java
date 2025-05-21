package es.mariana.acda.ut5.acda_ut5.model.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import es.mariana.acda.ut5.acda_ut5.model.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class LibroService {

    @Autowired
    private FirebaseService firebase;

    // 🔹 Crear un libro en Firestore
    public String crearLibro(Libro libro) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firebase.getFirestore().collection("libros").document();
        libro.setId(docRef.getId()); // Asignamos el ID generado
        docRef.set(libro).get();
        return docRef.getId();
    }

    // 🔹 Leer un libro por ID
    public Libro obtenerLibro(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firebase.getFirestore().collection("libros").document(id);
        DocumentSnapshot document = docRef.get().get();

        if (document.exists()) {
            return document.toObject(Libro.class);
        }
        return null; // No encontrado
    }

    // 🔹 Obtener todos los libros
    public Iterable<QueryDocumentSnapshot> obtenerTodosLibros() throws ExecutionException, InterruptedException {
        return firebase.getFirestore().collection("libros").get().get().getDocuments();
    }

    // 🔹 Actualizar un libro
    public boolean actualizarLibro(String id, Libro libro) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firebase.getFirestore().collection("libros").document(id);
        docRef.set(libro).get();
        return Boolean.TRUE;
    }

    // 🔹 Eliminar un libro por ID
    public boolean eliminarLibro(String id) {
        firebase.getFirestore().collection("libros").document(id).delete();
        return Boolean.TRUE;
    }
}
