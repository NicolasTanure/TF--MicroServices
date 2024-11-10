package com.example.tf.gestaoAssinatura.domain.model;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class UsuarioModel {
    private long codigo;
    private String usuario;
    private String senha;
}