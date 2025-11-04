package com.sandbox.Episteme.repository;

import com.sandbox.Episteme.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    public Optional<Resource> findByName(String name);}