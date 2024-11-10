package com.example.tf.gestaoAssinatura.adapters.controllers;

import com.example.tf.gestaoAssinatura.application.service.AssinaturaService;
import com.example.tf.gestaoAssinatura.application.service.PagamentoService;
import com.example.tf.gestaoAssinatura.adapters.dto.PaymentRequestDTO;
import com.example.tf.gestaoAssinatura.adapters.dto.RespostaPagamentoDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrarpagamento")
public class PaymentController {

    private final PagamentoService pagamentoService;

    public PaymentController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<?> registerPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        try {
            RespostaPagamentoDTO dto = pagamentoService.fazPagamento(paymentRequest);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar pagamento: " + e.getMessage());
        }
    }
}