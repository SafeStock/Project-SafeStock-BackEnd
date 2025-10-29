package com.example.safestock.application.port.in;

import com.example.safestock.domain.model.HistoricoAlertas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HistoricoAlertasUseCase {

    HistoricoAlertas criar(HistoricoAlertas historicoAlertas);

    // ✅ ADICIONAR: Métodos do back antigo
    List<HistoricoAlertas> findAlertasRecentes();
    List<HistoricoAlertas> listarHistorico();
    Page<HistoricoAlertas> listarPaginado(int page, int size);
}