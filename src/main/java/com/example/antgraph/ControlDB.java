package com.example.antgraph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlDB extends JpaRepository<Antmodel, Integer> {
    Antmodel findAllById(int i);
    Antmodel findAllByUsername(String whiteant);
}
