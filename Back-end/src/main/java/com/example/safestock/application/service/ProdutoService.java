package com.example.safestock.application.service;

import com.example.safestock.application.port.out.ProdutoRepository;
import com.example.safestock.domain.model.Produto;
import com.example.safestock.application.service.RegistroUsoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final RegistroUsoService registroUsoService;

    public ProdutoService(ProdutoRepository produtoRepository, RegistroUsoService registroUsoService) {
        this.produtoRepository = produtoRepository;
        this.registroUsoService = registroUsoService;
    }

    public void salvarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public void deletarPorId(Long id) {
        produtoRepository.deleteById(id);
    }

    public long contarProdutos() {
        return produtoRepository.count();
    }

    // ---- MÉTODOS DE KPI ----

    public List<Produto> listarProdutosProximosDaValidade() {
        LocalDate hoje = LocalDate.now();
        LocalDate seteDias = hoje.plusDays(7);
        return produtoRepository.findProdutosProximosDaValidade(hoje, seteDias);
    }

    public Long contarProdutosProximosDaValidade() {
        LocalDate hoje = LocalDate.now();
        LocalDate seteDias = hoje.plusDays(7);
        return produtoRepository.countProdutosProximosDaValidade(hoje, seteDias);
    }

    public List<Produto> listarProdutosProximosLimiteUso() {
        return produtoRepository.findProdutosProximosLimiteUso();
    }

    public Long contarProdutosProximosLimiteUso() {
        return produtoRepository.countProdutosProximosLimiteUso();
    }

    public Long contarProdutosCadastrados() {
        return produtoRepository.count();
    }

    public Long contarProdutosRetiradosDoEstoqueMesAtual() {
        return registroUsoService.contarProdutosRetiradosDoEstoqueMesAtual();
    }
}
