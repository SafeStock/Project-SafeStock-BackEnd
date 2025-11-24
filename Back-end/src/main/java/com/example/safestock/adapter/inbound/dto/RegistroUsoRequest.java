package com.example.safestock.adapter.inbound.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistroUsoRequest {

    @NotBlank(message = "Produto é obrigatório")
    private String produto;

    private LocalDate dataValidade;

    @Positive(message = "Quantidade deve ser maior que zero")
    private int quantidade;

    private LocalDateTime dataHoraSaida;

    @NotNull(message = "ID do funcionário é obrigatório")
    private Long funcionarioId;

    // getters e setters
    public String getProduto() { return produto; }
    public void setProduto(String produto) { this.produto = produto; }

    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataHoraSaida() { return dataHoraSaida; }
    public void setDataHoraSaida(LocalDateTime dataHoraSaida) { this.dataHoraSaida = dataHoraSaida; }

    public Long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Long funcionarioId) { this.funcionarioId = funcionarioId; }
}