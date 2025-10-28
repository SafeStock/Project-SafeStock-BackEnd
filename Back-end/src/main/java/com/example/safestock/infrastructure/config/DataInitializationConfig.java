package com.example.safestock.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuração de dados iniciais para desenvolvimento
 * Esta classe pode ser usada como alternativa ao data.sql para ter mais controle
 * sobre quando e como os dados são inseridos
 */
@Configuration
@Profile("dev") // Só executa no profile de desenvolvimento
public class DataInitializationConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializationConfig.class);

    /**
     * Bean que executa na inicialização da aplicação
     * Descomente e implemente se quiser usar inicialização programática
     * ao invés do data.sql
     */
    /*
    @Bean
    public CommandLineRunner initData(
            CrecheRepository crecheRepository,
            FuncionarioRepository funcionarioRepository,
            ProdutoRepository produtoRepository,
            RegistroUsoRepository registroUsoRepository) {
        
        return args -> {
            // Verifica se já existem dados para evitar duplicação
            if (crecheRepository.count() > 0) {
                logger.info("Dados já existem no banco. Pulando inicialização.");
                return;
            }

            logger.info("Iniciando inserção de dados iniciais...");

            // Aqui você pode implementar a lógica de inserção dos dados
            // usando as entidades e repositórios do JPA
            
            logger.info("Dados iniciais inseridos com sucesso!");
        };
    }
    */
}