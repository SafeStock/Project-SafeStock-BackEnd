package com.example.safestock.adapter.inbound.controller;

import com.example.safestock.adapter.inbound.dto.RegistroUsoRequest;
import com.example.safestock.adapter.inbound.dto.RegistroUsoResponse;
import com.example.safestock.application.port.in.RegistroUsoUseCase;
import com.example.safestock.domain.model.Funcionario;
import com.example.safestock.domain.model.RegistroUso;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registroUso")
public class RegistroUsoController {

    private final RegistroUsoUseCase useCase;

    public RegistroUsoController(RegistroUsoUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<RegistroUsoResponse> create(@RequestBody @Valid RegistroUsoRequest request) {
        RegistroUso registroUso = new RegistroUso();
        registroUso.setProduto(request.getProduto());
        registroUso.setDataValidade(request.getDataValidade());
        registroUso.setQuantidade(request.getQuantidade());
        registroUso.setDataHoraSaida(request.getDataHoraSaida());

        Funcionario funcionario = new Funcionario();
        funcionario.setId(request.getFuncionarioId());
        registroUso.setFuncionario(funcionario);

        RegistroUso saved = useCase.criar(registroUso);

        RegistroUsoResponse response = toResponse(saved);

        return ResponseEntity.created(URI.create("/api/registroUso/" + response.getId())).body(response);
    }

    @GetMapping("/paged")
    public ResponseEntity<?> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        // ✅ TEMPORÁRIO: Retornar lista simples
        List<RegistroUso> registros = useCase.listar();

        Map<String, Object> response = new HashMap<>();
        response.put("content", registros);
        response.put("page", 0);
        response.put("size", registros.size());
        response.put("totalPages", 1);
        response.put("totalElements", registros.size());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RegistroUsoResponse>> list() {
        List<RegistroUsoResponse> response = useCase.listar().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        useCase.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private RegistroUsoResponse toResponse(RegistroUso registroUso) {
        RegistroUsoResponse response = new RegistroUsoResponse();
        response.setId(registroUso.getId());
        response.setProduto(registroUso.getProduto());
        response.setDataValidade(registroUso.getDataValidade());
        response.setQuantidade(registroUso.getQuantidade());
        response.setDataHoraSaida(registroUso.getDataHoraSaida());
        response.setFuncionarioId(registroUso.getFuncionario() != null ?
                registroUso.getFuncionario().getId() : null);
        return response;
    }
}