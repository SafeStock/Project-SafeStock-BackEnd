package com.example.safestock.adapter.outbound.mapper;

import com.example.safestock.domain.model.Funcionario;
import com.example.safestock.domain.model.RegistroUso;
import com.example.safestock.domain.model.Relatorio;
import com.example.safestock.infrastructure.entity.FuncionarioEntity;
import com.example.safestock.infrastructure.entity.RegistroUsoEntity;
import com.example.safestock.infrastructure.entity.RelatorioEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RegistroUsoMapper {

    public static RegistroUsoEntity toEntity(RegistroUso domain) {
        if (domain == null) return null;

        RegistroUsoEntity entity = new RegistroUsoEntity();
        entity.setId(domain.getId());
        entity.setProduto(domain.getProduto());
        entity.setDataValidade(domain.getDataValidade());
        entity.setQuantidade(domain.getQuantidade());
        entity.setDataHoraSaida(domain.getDataHoraSaida());

        // ✅ Converte Funcionario → FuncionarioEntity (apenas ID para evitar recursão)
        if (domain.getFuncionario() != null && domain.getFuncionario().getId() != null) {
            FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
            funcionarioEntity.setId(domain.getFuncionario().getId());
            entity.setFuncionario(funcionarioEntity);
        }

        // ⚠️ Relatorios são geralmente gerenciados separadamente
        // Para evitar problemas de recursão, não mapeamos aqui
        entity.setRelatorio(Collections.emptyList());

        return entity;
    }

    public static RegistroUso toDomain(RegistroUsoEntity entity) {
        if (entity == null) return null;

        RegistroUso domain = new RegistroUso();
        domain.setId(entity.getId());
        domain.setProduto(entity.getProduto());
        domain.setDataValidade(entity.getDataValidade());
        domain.setQuantidade(entity.getQuantidade());
        domain.setDataHoraSaida(entity.getDataHoraSaida());

        // ✅ Converte FuncionarioEntity → Funcionario (apenas dados básicos)
        if (entity.getFuncionario() != null) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(entity.getFuncionario().getId());
            funcionario.setNome(entity.getFuncionario().getNome());
            funcionario.setSobrenome(entity.getFuncionario().getSobrenome());
            domain.setFuncionario(funcionario);
        }

        // ⚠️ Relatorios são carregados sob demanda se necessário
        domain.setRelatorio(Collections.emptyList());

        return domain;
    }
}