package com.example.safestock.application.service;

import com.example.safestock.application.port.in.RegistroUsoUseCase;
import com.example.safestock.application.port.out.FuncionarioRepository;
import com.example.safestock.application.port.out.RegistroUsoRepository;
import com.example.safestock.domain.model.RegistroUso;
import com.example.safestock.adapter.outbound.error.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistroUsoService implements RegistroUsoUseCase {

    private final RegistroUsoRepository registroUsoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public RegistroUsoService(RegistroUsoRepository registroUsoRepository,
                              FuncionarioRepository funcionarioRepository) {
        this.registroUsoRepository = registroUsoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    @Transactional
    public RegistroUso criar(RegistroUso registroUso) {

        if (registroUso.getFuncionario() == null || registroUso.getFuncionario().getId() == null) {
            throw new IllegalArgumentException("Funcionário é obrigatório");
        }

        Long funcionarioId = registroUso.getFuncionario().getId();

        if (!funcionarioRepository.existsById(funcionarioId)) {
            throw new NotFoundException("Funcionário com ID " + funcionarioId + " não encontrado");
        }

        if (registroUso.getProduto() == null || registroUso.getProduto().trim().isEmpty()) {
            throw new IllegalArgumentException("Produto é obrigatório");
        }

        if (registroUso.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (registroUso.getDataHoraSaida() == null) {
            registroUso.setDataHoraSaida(java.time.LocalDateTime.now());
        }

        return registroUsoRepository.save(registroUso);
    }

    @Override
    public List<RegistroUso> listar() {
        return registroUsoRepository.findAll();
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        registroUsoRepository.deleteById(id);
    }

    public Long contarProdutosRetiradosDoEstoqueMesAtual() {
        LocalDate agora = LocalDate.now();
        return registroUsoRepository.sumQuantidadeRegistroDeUsoMes(agora.getYear(), agora.getMonthValue());
    }
}