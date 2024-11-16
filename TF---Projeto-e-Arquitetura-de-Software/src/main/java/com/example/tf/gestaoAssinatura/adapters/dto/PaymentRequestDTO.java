package com.example.tf.gestaoAssinatura.adapters.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private LocalDate data;
    private Long assinaturaId;
    private double valorPago;
    private String promocao;
}
