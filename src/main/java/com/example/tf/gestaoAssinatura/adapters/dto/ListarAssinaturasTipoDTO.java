package com.example.tf.gestaoAssinatura.adapters.dto;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ListarAssinaturasTipoDTO {
    private long codigoAssinatura;
    private long codigoCliente;
    private long codigoApp;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;
}
