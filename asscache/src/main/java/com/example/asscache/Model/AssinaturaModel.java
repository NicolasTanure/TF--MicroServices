package com.example.asscache.Model;

import java.time.LocalDate;
import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssinaturaModel {
    private long codigo;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;

}

