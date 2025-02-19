package com.example.demo.service;



import com.example.demo.entity.Grupo;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class GrupoService {

    @Autowired
    private FirebaseService firebase;
    // Crear un grupo en Firestore
    public String crearGrupo(Grupo grupo) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firebase.getFirestore().collection("grupos").document();
        grupo.setId(docRef.getId()); // Asignamos el ID generado
        docRef.set(grupo).get();
        return docRef.getId();
    }
    // Leer un grupo por ID
    public Grupo obtenerGrupo(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firebase.getFirestore().collection("grupos").document(id);
        DocumentSnapshot document = docRef.get().get();
        if (document.exists()) {
            return document.toObject(Grupo.class);
        }
        return null; // No encontrado
    }
    // Obtener todos los grupos
    public Iterable<QueryDocumentSnapshot> obtenerTodosGrupos() throws ExecutionException, InterruptedException {
        return firebase.getFirestore().collection("grupos").get().get().getDocuments();
    }

    // Actualizar un grupo
    public boolean actualizarGrupo(String id, Grupo grupo) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firebase.getFirestore().collection("grupos").document(id);
        docRef.set(grupo).get();
        return Boolean.TRUE;
    }
    // Eliminar un libro por ID
    public boolean eliminarGrupo(String id) {
        firebase.getFirestore().collection("grupos").document(id).delete();
        return Boolean.TRUE;
    }



    /*
    private static final String COLLECTION_NAME = "Grupos";
    private final Firestore db = FirestoreClient.getFirestore();

    public String addGrupo(Grupo grupo) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
        grupo.setId(docRef.getId());
        ApiFuture<WriteResult> future = docRef.set(grupo);
        return future.get().getUpdateTime().toString();
    }

    public List<Grupo> getAllGrupos() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        return future.get().getDocuments().stream()
                .map(doc -> doc.toObject(Grupo.class))
                .collect(Collectors.toList());
    }

    public Grupo getGrupoById(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = db.collection(COLLECTION_NAME).document(id).get().get();
        return document.exists() ? document.toObject(Grupo.class) : null;
    }

    public String updateGrupo(String id, Grupo grupo) throws ExecutionException, InterruptedException {
        grupo.setId(id);
        ApiFuture<WriteResult> future = db.collection(COLLECTION_NAME).document(id).set(grupo);
        return future.get().getUpdateTime().toString();
    }

    public String deleteGrupo(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection(COLLECTION_NAME).document(id).delete();
        return future.get().getUpdateTime().toString();
    }*/
}
