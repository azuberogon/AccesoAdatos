package com.example.demo.service;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseService {
    @PostConstruct
    public void initializeFirebaseService() {
        try {
            if (FirebaseApp.getApps().isEmpty()) { // Evitar inicialización múltiple
                InputStream serviceAccount = new ClassPathResource("serviceAccountKey.json").getInputStream();
                FirebaseOptions options = FirebaseOptions.builder()

                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(options);
                System.out.println("🔥 Firebase inicializado correctamente");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar Firebase: " + e.getMessage());
        }
    }
    public Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}

