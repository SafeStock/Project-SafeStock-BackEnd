package com.example.safestock.application.service;

import com.example.safestock.application.port.in.RegistroUsoUseCase;
import com.example.safestock.application.port.out.RegistroUsoRepository;
import com.example.safestock.domain.model.RegistroUso;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistroUsoService implements RegistroUsoUseCase {

    private final RegistroUsoRepository registroUsoRepository;

    public RegistroUsoService(RegistroUsoRepository registroUsoRepository) {
        this.registroUsoRepository = registroUsoRepository;
    }

    @Override
    public RegistroUso criar(RegistroUso registroUso) {
        return registroUsoRepository.save(registroUso);
    }

    @Override
    public List<RegistroUso> listar() {
        return registroUsoRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        registroUsoRepository.deleteById(id);
    }

    public Long contarProdutosRetiradosDoEstoqueMesAtual() {
        LocalDate agora = LocalDate.now();
        return registroUsoRepository.sumQuantidadeRegistroDeUsoMes(agora.getYear(), agora.getMonthValue());
    }
}
