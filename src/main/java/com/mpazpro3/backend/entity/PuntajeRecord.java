package com.mpazpro3.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "puntajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PuntajeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer estudianteId;
    private String nombre;
    private Integer puntaje;
    private String asignatura;
}