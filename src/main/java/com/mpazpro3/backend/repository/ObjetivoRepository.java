package com.mpazpro3.backend.repository;

import com.mpazpro3.backend.entity.ObjetivoAprendizaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjetivoRepository extends JpaRepository<ObjetivoAprendizaje, String> {
    List<ObjetivoAprendizaje> findByAsignaturaCodigo(String asignaturaCodigo);
}