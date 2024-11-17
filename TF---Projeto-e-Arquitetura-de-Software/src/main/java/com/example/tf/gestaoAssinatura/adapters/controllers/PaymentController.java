package com.example.tf.gestaoAssinatura.adapters.controllers;

import com.example.tf.gestaoAssinatura.application.service.AssinaturaService;
import com.example.tf.gestaoAssinatura.application.service.PagamentoService;
import com.example.tf.gestaoAssinatura.adapters.dto.PaymentRequestDTO;
import com.example.tf.gestaoAssinatura.adapters.dto.RespostaPagamentoDTO;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrarpagamento")
public class PaymentController {

    private final PagamentoService pagamentoService;
    private RabbitTemplate rabbitTemplate; 
    private FanoutExchange fanout;

    public PaymentController(PagamentoService pagamentoService, RabbitTemplate rabbitTemplate, FanoutExchange fanout) {
        this.pagamentoService = pagamentoService;
        this.rabbitTemplate = rabbitTemplate;
        this.fanout = fanout;
    }

    @PostMapping
    public ResponseEntity<?> registerPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        try {
            RespostaPagamentoDTO dto = pagamentoService.fazPagamento(paymentRequest);
            rabbitTemplate.convertAndSend(fanout.getName(), "", paymentRequest.getAssinaturaId());
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar pagamento: " + e.getMessage());
        }
    }
}