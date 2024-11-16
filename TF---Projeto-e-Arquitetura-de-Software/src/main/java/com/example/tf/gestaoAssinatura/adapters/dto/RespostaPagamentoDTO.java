package com.example.tf.gestaoAssinatura.adapters.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Builder
@Getter
@Setter
public class RespostaPagamentoDTO {
    @JsonProperty("data")
    LocalDate data;
    @JsonProperty("valor_estornado")
    Double valor;
}