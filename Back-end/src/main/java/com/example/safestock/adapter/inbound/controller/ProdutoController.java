package com.example.safestock.adapter.inbound.controller;

import com.example.safestock.application.service.ProdutoService;
import com.example.safestock.domain.model.Produto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
@SecurityRequirement(name = "Bearer")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> criarProduto(@RequestBody Produto produto) {
        produtoService.salvarProduto(produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/paged")
    public ResponseEntity<?> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        // ✅ TEMPORÁRIO: Retornar lista simples
        List<Produto> produtos = produtoService.listarTodos();

        Map<String, Object> response = new HashMap<>();
        response.put("content", produtos);
        response.put("page", 0);
        response.put("size", produtos.size());
        response.put("totalPages", 1);
        response.put("totalElements", produtos.size());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        return produtoService.buscarPorId(id)
                .map(produtoExistente -> {
                    produtoAtualizado.setId(id);
                    produtoService.salvarProduto(produtoAtualizado);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar/{id}")
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

    @GetMapping("/kpi/proximosvalidade")
    public ResponseEntity<List<Produto>> listarProdutosProximosDaValidade() {
        List<Produto> produtos = produtoService.listarProdutosProximosDaValidade();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/kpi/totalproximosvalidade")
    public ResponseEntity<Long> contarProdutosProximosDaValidade() {
        return ResponseEntity.ok(produtoService.contarProdutosProximosDaValidade());
    }

    @GetMapping("/kpi/proximoslimite")
    public ResponseEntity<List<Produto>> listarProdutosProximosLimiteUso() {
        List<Produto> produtos = produtoService.listarProdutosProximosLimiteUso();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/kpi/totalproximoslimite")
    public ResponseEntity<Long> contarProdutosProximosLimiteUso() {
        return ResponseEntity.ok(produtoService.contarProdutosProximosLimiteUso());
    }

    @GetMapping("/kpi/totalprodutos")
    public ResponseEntity<Long> contarProdutosCadastrados() {
        return ResponseEntity.ok(produtoService.contarProdutosCadastrados());
    }

    @GetMapping("/kpi/totalretiradoestoque")
    public ResponseEntity<Long> contarProdutosRetiradosDoEstoque() {
        return ResponseEntity.ok(produtoService.contarProdutosRetiradosDoEstoqueMesAtual());
    }
}
