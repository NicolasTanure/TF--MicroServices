package com.example.tf.gestaoAssinatura.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteModel {
    private long codigo;
    private String nome;
    private String email;
}

