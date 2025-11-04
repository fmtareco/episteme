package com.sandbox.Episteme.repository;

import com.sandbox.Episteme.model.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DimensionRepository extends JpaRepository<Dimension, Long> {

    public Optional<Dimension> findByName(String name);


}