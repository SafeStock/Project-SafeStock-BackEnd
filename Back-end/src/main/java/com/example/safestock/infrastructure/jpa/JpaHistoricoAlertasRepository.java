package com.example.safestock.infrastructure.jpa;

import com.example.safestock.infrastructure.entity.HistoricoAlertasEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaHistoricoAlertasRepository extends JpaRepository<HistoricoAlertasEntity, Long> {

    List<HistoricoAlertasEntity> findByDataHoraAfter(LocalDateTime data);
}