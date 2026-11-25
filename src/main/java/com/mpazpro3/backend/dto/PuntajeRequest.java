package com.mpazpro3.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PuntajeRequest {
    private Integer estudianteId;
    private String nombre;
    private Integer puntaje;
    private String asignatura;
}