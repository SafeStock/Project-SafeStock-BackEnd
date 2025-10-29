package com.example.safestock.adapter.outbound.mapper;

import com.example.safestock.domain.model.Funcionario;
import com.example.safestock.infrastructure.entity.FuncionarioEntity;

public class FuncionarioMapper {

    public static Funcionario toDomain(FuncionarioEntity entity) {
        if (entity == null) return null;

        Funcionario domain = new Funcionario();
        domain.setId(entity.getId());
        domain.setNome(entity.getNome());
        domain.setSobrenome(entity.getSobrenome());
        domain.setEmail(entity.getEmail());
        domain.setCargo(entity.getCargo());
        domain.setTelefone(entity.getTelefone());
        domain.setSenha(entity.getSenha());

        if (entity.getCreche() != null) {
        }

        return domain;
    }

    public static FuncionarioEntity toEntity(Funcionario domain) {
        if (domain == null) return null;

        FuncionarioEntity entity = new FuncionarioEntity();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setSobrenome(domain.getSobrenome());
        entity.setEmail(domain.getEmail());
        entity.setCargo(domain.getCargo());
        entity.setTelefone(domain.getTelefone());
        entity.setSenha(domain.getSenha());

        return entity;
    }
}