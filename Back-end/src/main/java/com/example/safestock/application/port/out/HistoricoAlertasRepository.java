package com.example.safestock.application.port.out;

import com.example.safestock.domain.model.HistoricoAlertas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoricoAlertasRepository {

    HistoricoAlertas save(HistoricoAlertas historicoAlertas);

    // ✅ ADICIONAR: Métodos do back antigo
    List<HistoricoAlertas> findAll();
    List<HistoricoAlertas> findByDataHoraAfter(LocalDateTime data);
    Page<HistoricoAlertas> findAll(Pageable pageable);
}