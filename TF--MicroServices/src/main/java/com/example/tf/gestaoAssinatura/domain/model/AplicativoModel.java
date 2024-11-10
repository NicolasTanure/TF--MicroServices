package com.example.tf.gestaoAssinatura.domain.model;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AplicativoModel {
    private long codigo;
    private String nome;
    private Double custoMensal;
}
