package com.mpazpro3.backend.controller;

import com.mpazpro3.backend.dto.PuntajeRequest;
import com.mpazpro3.backend.dto.PuntajeResponse;
import com.mpazpro3.backend.entity.PuntajeRecord;
import com.mpazpro3.backend.repository.PuntajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puntajes")
@CrossOrigin(origins = "*")
public class PuntajeController {

    @Autowired
    private PuntajeRepository puntajeRepository;

    // CREATE - Guarda el puntaje enviado desde la app Android
    @PostMapping
    public ResponseEntity<PuntajeResponse> guardarPuntaje(@RequestBody PuntajeRequest request) {
        PuntajeRecord record = new PuntajeRecord(
                null,
                request.getEstudianteId(),
                request.getNombre(),
                request.getPuntaje(),
                request.getAsignatura()
        );
        PuntajeRecord guardado = puntajeRepository.save(record);

        PuntajeResponse response = new PuntajeResponse(
                guardado.getId(),
                true,
                "Puntaje registrado exitosamente en Spring Boot"
        );
        return ResponseEntity.ok(response);
    }

    // READ - Lista todos los puntajes almacenados
    @GetMapping
    public List<PuntajeRecord> listarPuntajes() {
        return puntajeRepository.findAll();
    }

    // UPDATE - Actualiza un puntaje existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<PuntajeRecord> actualizarPuntaje(
            @PathVariable Integer id,
            @RequestBody PuntajeRequest request) {
        return puntajeRepository.findById(id)
                .map(existente -> {
                    existente.setPuntaje(request.getPuntaje());
                    existente.setAsignatura(request.getAsignatura());
                    return ResponseEntity.ok(puntajeRepository.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Elimina un puntaje por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuntaje(@PathVariable Integer id) {
        if (!puntajeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        puntajeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}