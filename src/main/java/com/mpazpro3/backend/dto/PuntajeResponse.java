package com.mpazpro3.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PuntajeResponse {
    private Integer id;
    private Boolean success;
    private String message;
}