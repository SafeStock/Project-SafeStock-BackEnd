package com.example.safestock.application.port.out;

import com.example.safestock.domain.model.HistoricoAlertas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import java.util.List;

public interface HistoricoAlertasRepository {

    HistoricoAlertas save (HistoricoAlertas historicoAlertas);
    
    List<HistoricoAlertas> findAll();
}
