package es.mariana.acda.ut5.acda_ut5.model.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import es.mariana.acda.ut5.acda_ut5.model.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static es.mariana.acda.ut5.acda_ut5.utils.GenericUtils.fromQueryDocumentSnapshotToLibros;

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
    public List<Libro> obtenerTodosLibros() throws ExecutionException, InterruptedException {
        Query query = firebase.getFirestore().collection("libros");
        return fromQueryDocumentSnapshotToLibros(query.get().get().getDocuments());
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

    // 🔹 Filtrando documentos básicos
    public List<Libro> obtenerLibrosPorAutor(String autor) throws ExecutionException, InterruptedException {
        Query query = firebase.getFirestore().collection("libros")
                .whereEqualTo("autor", autor);
        return fromQueryDocumentSnapshotToLibros(query.get().get().getDocuments());
    }

    public List<Libro> obtenerLibrosConMasPaginasDe(Integer paginas) throws ExecutionException, InterruptedException {
        Query query = firebase.getFirestore().collection("libros")
                .whereGreaterThanOrEqualTo("paginas", paginas);
        return fromQueryDocumentSnapshotToLibros(query.get().get().getDocuments());
    }

    public List<Libro> obtenerLibrosConMenosPaginasDe(Integer paginas) throws ExecutionException, InterruptedException {
        Query query = firebase.getFirestore().collection("libros")
                .whereLessThan("paginas", paginas);
        return fromQueryDocumentSnapshotToLibros(query.get().get().getDocuments());
    }

    public List<Libro> obtenerLibrosDisponiblesPorAutor(String autor) throws ExecutionException, InterruptedException {
        Query query = firebase.getFirestore().collection("libros")
                .whereEqualTo("disponible", true)
                .whereEqualTo("autor", autor)
                .orderBy("paginas", Query.Direction.DESCENDING)
                .startAt(2) // Desde la que empieza
                .limit(1); // Límite de resultados

        return fromQueryDocumentSnapshotToLibros(query.get().get().getDocuments());
    }
}
