package es.mariana.acda.ut5.acda_ut5.controller;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import es.mariana.acda.ut5.acda_ut5.model.entity.Libro;
import es.mariana.acda.ut5.acda_ut5.model.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping
    public ResponseEntity<String> crearLibro(@RequestBody Libro libro) throws ExecutionException, InterruptedException {
        String idLibro = libroService.crearLibro(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Libro creado con ID: " + idLibro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibro(@PathVariable String id) throws ExecutionException, InterruptedException {
        Libro libro = libroService.obtenerLibro(id);
        return libro != null
                ? ResponseEntity.ok(libro)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() throws ExecutionException, InterruptedException {
        List<Libro> libros = new ArrayList<>();
        for (QueryDocumentSnapshot document : libroService.obtenerTodosLibros()) {
            libros.add(document.toObject(Libro.class));
        }
        return ResponseEntity.ok(libros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarLibro(@PathVariable String id, @RequestBody Libro libro)
            throws ExecutionException, InterruptedException {
        boolean actualizado = libroService.actualizarLibro(id, libro);
        return actualizado
                ? ResponseEntity.ok("Libro actualizado correctamente.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLibro(@PathVariable String id) {
        boolean eliminado = libroService.eliminarLibro(id);
        return eliminado
                ? ResponseEntity.ok("Libro eliminado correctamente.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado.");
    }
}
