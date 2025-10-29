package com.example.safestock.adapter.outbound.repository;

import com.example.safestock.adapter.outbound.mapper.HistoricoAlertasMapper;
import com.example.safestock.application.port.out.HistoricoAlertasRepository;
import com.example.safestock.domain.model.HistoricoAlertas;
import com.example.safestock.infrastructure.entity.HistoricoAlertasEntity;
import com.example.safestock.infrastructure.jpa.JpaHistoricoAlertasRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HistoricoAlertasRepositoryImpl implements HistoricoAlertasRepository {

    private final JpaHistoricoAlertasRepository jpa;

    public HistoricoAlertasRepositoryImpl(JpaHistoricoAlertasRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public HistoricoAlertas save(HistoricoAlertas historicoAlertas) {
        HistoricoAlertasEntity entity = HistoricoAlertasMapper.toEntity(historicoAlertas);
        HistoricoAlertasEntity saved = jpa.save(entity);
        return HistoricoAlertasMapper.toDomain(saved);
    }

    // ✅ ADICIONAR: Implementação dos métodos do back antigo
    @Override
    public List<HistoricoAlertas> findAll() {
        return jpa.findAll().stream()
                .map(HistoricoAlertasMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoricoAlertas> findByDataHoraAfter(LocalDateTime data) {
        return jpa.findByDataHoraAfter(data).stream()
                .map(HistoricoAlertasMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Page<HistoricoAlertas> findAll(Pageable pageable) {
        return jpa.findAll(pageable)
                .map(HistoricoAlertasMapper::toDomain);
    }
}