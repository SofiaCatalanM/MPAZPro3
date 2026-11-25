package com.mpazpro3.backend.repository;

import com.mpazpro3.backend.entity.PuntajeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntajeRepository extends JpaRepository<PuntajeRecord, Integer> {
}