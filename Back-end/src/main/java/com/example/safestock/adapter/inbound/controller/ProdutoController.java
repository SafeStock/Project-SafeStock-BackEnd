package com.example.safestock.adapter.inbound.controller;

import com.example.safestock.application.service.ProdutoService;
import com.example.safestock.domain.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // -------------------------------
    // CRUD BÁSICO
    // -------------------------------

    @PostMapping
    public ResponseEntity<Void> criarProduto(@RequestBody Produto produto) {
        produtoService.salvarProduto(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        return produtoService.buscarPorId(id)
                .map(produtoExistente -> {
                    produtoAtualizado.setId(id);
                    produtoService.salvarProduto(produtoAtualizado);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (produtoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarProdutos() {
        return ResponseEntity.ok(produtoService.contarProdutos());
    }

    // -------------------------------
    // ENDPOINTS DE KPI
    // -------------------------------

    @GetMapping("/kpi/proximos-da-validade")
    public ResponseEntity<List<Produto>> listarProdutosProximosDaValidade() {
        List<Produto> produtos = produtoService.listarProdutosProximosDaValidade();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/kpi/proximos-da-validade/count")
    public ResponseEntity<Long> contarProdutosProximosDaValidade() {
        return ResponseEntity.ok(produtoService.contarProdutosProximosDaValidade());
    }

    @GetMapping("/kpi/proximos-limite-uso")
    public ResponseEntity<List<Produto>> listarProdutosProximosLimiteUso() {
        List<Produto> produtos = produtoService.listarProdutosProximosLimiteUso();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/kpi/proximos-limite-uso/count")
    public ResponseEntity<Long> contarProdutosProximosLimiteUso() {
        return ResponseEntity.ok(produtoService.contarProdutosProximosLimiteUso());
    }
}
