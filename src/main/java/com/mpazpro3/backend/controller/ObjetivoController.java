package com.mpazpro3.backend.controller;

import com.mpazpro3.backend.entity.ObjetivoAprendizaje;
import com.mpazpro3.backend.repository.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objetivos")
@CrossOrigin(origins = "*")
public class ObjetivoController {

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @GetMapping
    public List<ObjetivoAprendizaje> obtenerTodos() {
        return objetivoRepository.findAll();
    }

    @GetMapping("/asignatura/{codigo}")
    public List<ObjetivoAprendizaje> obtenerPorAsignatura(@PathVariable String codigo) {
        return objetivoRepository.findByAsignaturaCodigo(codigo);
    }

    @PostMapping
    public ObjetivoAprendizaje crearObjetivo(@RequestBody ObjetivoAprendizaje objetivo) {
        return objetivoRepository.save(objetivo);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminarObjetivo(@PathVariable String codigo) {
        if (!objetivoRepository.existsById(codigo)) {
            return ResponseEntity.notFound().build();
        }
        objetivoRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}