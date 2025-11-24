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

        if (e.getCreche() != null) {
            Creche c = new Creche();
            c.setId(e.getCreche().getId());
            c.setNome(e.getCreche().getNome());
            d.setCreche(c);
        }

        // Não mapear registrosUso para evitar LazyInitializationException
        // Os registros não são necessários para autenticação e dashboard

        return entity;
    }
}