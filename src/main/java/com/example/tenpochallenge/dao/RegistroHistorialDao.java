package com.example.tenpochallenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tenpochallenge.entity.RegistroHistorial;

public interface RegistroHistorialDao extends JpaRepository<RegistroHistorial, Long> {
}
