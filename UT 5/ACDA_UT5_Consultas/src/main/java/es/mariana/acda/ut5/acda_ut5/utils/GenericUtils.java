package es.mariana.acda.ut5.acda_ut5.utils;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import es.mariana.acda.ut5.acda_ut5.model.entity.Libro;

import java.util.ArrayList;
import java.util.List;

public class GenericUtils {

    public static List<Libro> fromQueryDocumentSnapshotToLibros(List<QueryDocumentSnapshot> documentSnapshots) {
        List<Libro> libros = new ArrayList<>();
        for (QueryDocumentSnapshot document : documentSnapshots)
            libros.add(document.toObject(Libro.class));
        return libros;
    }
}
