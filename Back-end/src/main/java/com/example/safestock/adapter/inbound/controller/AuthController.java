package com.example.safestock.adapter.inbound.controller;

import com.example.safestock.application.port.in.FuncionarioUseCase;
import com.example.safestock.domain.model.Funcionario;
import com.example.safestock.infrastructure.security.GerenciadorTokenJwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/funcionarios")
public class AuthController {

    private final FuncionarioUseCase funcionarioUseCase;
    private final GerenciadorTokenJwt tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(FuncionarioUseCase funcionarioUseCase,
                          GerenciadorTokenJwt tokenService,
                          AuthenticationManager authenticationManager) {
        this.funcionarioUseCase = funcionarioUseCase;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Autentica usando AuthenticationManager
        UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());

        Authentication authentication = authenticationManager.authenticate(credentials);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Busca o funcionário no banco (para pegar dados completos, como nome e cargo)
        Funcionario funcionario = funcionarioUseCase.buscarFuncionarioPorEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email não cadastrado"));

        // Gera token JWT
        String token = tokenService.gerarToken(authentication);

        // Retorna dados do funcionário + token
        LoginResponse response = new LoginResponse(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getEmail(),
                funcionario.getCargo().name(),
                token
        );

        return ResponseEntity.ok(response);
    }

    // DTOs
    public static class LoginRequest {
        private String email;
        private String senha;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    public static class LoginResponse {
        private Long id;
        private String nome;
        private String sobrenome;
        private String email;
        private String cargo;
        private String token;

        public LoginResponse(Long id, String nome, String sobrenome, String email, String cargo, String token) {
            this.id = id;
            this.nome = nome;
            this.sobrenome = sobrenome;
            this.email = email;
            this.cargo = cargo;
            this.token = token;
        }

        public Long getId() { return id; }
        public String getNome() { return nome; }
        public String getSobrenome() { return sobrenome; }
        public String getEmail() { return email; }
        public String getCargo() { return cargo; }
        public String getToken() { return token; }
    }
}
