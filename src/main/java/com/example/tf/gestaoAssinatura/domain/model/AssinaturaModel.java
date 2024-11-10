package com.example.tf.gestaoAssinatura.domain.model;

import java.time.LocalDate;
import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssinaturaModel {
    private long codigo;
    private AplicativoModel aplicativo;
    private ClienteModel cliente;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;

    public AplicativoModel getAplicativoModel(){
        return aplicativo;
    }
}
