package com.example.demo.service;

import com.example.demo.entity.Cancion;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CancionService {
    @Autowired
    private FirebaseService firebase;

    // Crear un libro en Firestore
    public String crearCancion(Cancion cancion) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firebase.getFirestore().collection("Canciones").document();
        cancion.setId(docRef.getId()); // Asignamos el ID generado
        docRef.set(cancion).get();
        return docRef.getId();
    }
    public Cancion obtenerCancion(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firebase.getFirestore().collection("Canciones").document(id);
        DocumentSnapshot document = docRef.get().get();
        if (document.exists()) {
            return document.toObject(Cancion.class);
        }
        return null; // No encontrado
    }
    // Obtener todos los Canciones
    public Iterable<QueryDocumentSnapshot> obtenerTodasCanciones() throws ExecutionException, InterruptedException {
        return firebase.getFirestore().collection("Canciones").get().get().getDocuments();
    }
    public boolean actualizarCanciones(String id, Cancion cancion) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firebase.getFirestore().collection("Canciones").document(id);
        docRef.set(cancion).get();
        return Boolean.TRUE;
    }
    // Eliminar un libro por ID
    public boolean eliminarCancion(String id) {
        firebase.getFirestore().collection("Canciones").document(id).delete();
        return Boolean.TRUE;
    }




    public Cancion buscarCancion(String nombreCancion) {
        return new Cancion();
    }
}
