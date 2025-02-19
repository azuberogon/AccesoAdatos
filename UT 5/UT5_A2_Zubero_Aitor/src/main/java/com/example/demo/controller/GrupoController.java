package com.example.demo.controller;




import com.example.demo.entity.Grupo;
import com.example.demo.service.GrupoService;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;
    @PostMapping
    public ResponseEntity<String> crearGrupo(@RequestBody Grupo grupo) throws ExecutionException, InterruptedException {
        System.out.println("Grupo recibido: " + grupo); // Para debug
        String idGrupo = grupoService.crearGrupo(grupo);
        return ResponseEntity.status(HttpStatus.CREATED).body("grupo creado con ID: " + idGrupo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Grupo> obtenerGrupo(@PathVariable String id) throws ExecutionException, InterruptedException {
        Grupo grupo = grupoService.obtenerGrupo(id);
        return grupo != null
                ? ResponseEntity.ok(grupo)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @GetMapping
    public ResponseEntity<List<Grupo>> obtenerTodosLosGrupos() throws ExecutionException, InterruptedException {
        List<Grupo> grupos = new ArrayList<>();
        for (QueryDocumentSnapshot document : grupoService.obtenerTodosGrupos()) {
            grupos.add(document.toObject(Grupo.class));
        }
        return ResponseEntity.ok(grupos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarGrupo(@PathVariable String id, @RequestBody Grupo grupo)
            throws ExecutionException, InterruptedException {
        boolean actualizado = grupoService.actualizarGrupo(id, grupo);
        return actualizado
                ? ResponseEntity.ok("Grupo actualizado correctamente.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo no encontrado.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarGrupo(@PathVariable String id) {
        boolean eliminado = grupoService.eliminarGrupo(id);
        return eliminado
                ? ResponseEntity.ok("Grupo eliminado correctamente.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo no encontrado.");
    }
}
