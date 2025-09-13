package com.example.safestock.infrastructure.jpa;

import com.example.safestock.infrastructure.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaFuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    Optional<FuncionarioEntity> findByEmail(String email);
}
