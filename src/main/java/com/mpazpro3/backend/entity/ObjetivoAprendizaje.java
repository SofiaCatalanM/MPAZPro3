package com.mpazpro3.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "objetivos_aprendizaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjetivoAprendizaje {

    @Id
    private String codigo;

    private String descripcion;
    private String asignaturaCodigo;
    private Integer unidad;
    private String ejeCurricular;
}