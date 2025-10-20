package com.example.safestock.application.port.out;

import com.example.safestock.domain.model.Produto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    void save(Produto produto);

    List<Produto> findAll();

    Optional<Produto> findById(Long id);

    void deleteById(Long id);

    long count();

    // KPI
    List<Produto> findProdutosProximosDaValidade(LocalDate inicio, LocalDate fim);

    Long countProdutosProximosDaValidade(LocalDate inicio, LocalDate fim);

    List<Produto> findProdutosProximosLimiteUso();

    Long countProdutosProximosLimiteUso();
}
