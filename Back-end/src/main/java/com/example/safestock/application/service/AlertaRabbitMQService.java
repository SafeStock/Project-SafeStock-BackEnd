package com.example.safestock.application.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertaRabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    public AlertaRabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarAlerta(String mensagem) {
        rabbitTemplate.convertAndSend(
                "alertas-exchange",  // ✅ Exchange
                "alerta.novo",       // ✅ Routing key
                mensagem             // ✅ Mensagem
        );
        System.out.println("📩 Mensagem enviada ao RabbitMQ: " + mensagem);
    }
}