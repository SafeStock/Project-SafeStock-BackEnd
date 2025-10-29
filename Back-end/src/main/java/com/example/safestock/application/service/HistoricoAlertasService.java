package com.example.safestock.application.service;

import com.example.safestock.application.port.in.HistoricoAlertasUseCase;
import com.example.safestock.application.port.out.HistoricoAlertasRepository;
import com.example.safestock.domain.model.HistoricoAlertas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoAlertasService implements HistoricoAlertasUseCase {

    private final HistoricoAlertasRepository historicoAlertasRepository;

    public HistoricoAlertasService(HistoricoAlertasRepository historicoAlertasRepository) {
        this.historicoAlertasRepository = historicoAlertasRepository;
    }

    @Override
    public HistoricoAlertas criar(HistoricoAlertas historicoAlertas) {
        return historicoAlertasRepository.save(historicoAlertas);
    }

    // ✅ ADICIONAR: Métodos do back antigo
    @Override
    public List<HistoricoAlertas> findAlertasRecentes() {
        LocalDateTime umMesAtras = LocalDateTime.now().minusMonths(1);
        return historicoAlertasRepository.findByDataHoraAfter(umMesAtras)
                .stream()
                .sorted((a, b) -> b.getDataHora().compareTo(a.getDataHora()))
                .toList();
    }

    @Override
    public List<HistoricoAlertas> listarHistorico() {
        return historicoAlertasRepository.findAll();
    }

    @Override
    public Page<HistoricoAlertas> listarPaginado(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataHora").descending());
        return historicoAlertasRepository.findAll(pageable);
    }
}