package com.example.tf.gestaoAssinatura.domain.model;


import java.time.LocalDate;
import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagamentoModel {
    private long codigo;
    private AssinaturaModel assinatura;
    private Double valorPago;
    private LocalDate dataPagamento;
    private String promocao;
}

