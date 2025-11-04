package com.sandbox.Episteme.repository;

import com.sandbox.Episteme.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DomainRepository extends JpaRepository<Domain, Long> {
    public Optional<Domain> findByName(String name);
}