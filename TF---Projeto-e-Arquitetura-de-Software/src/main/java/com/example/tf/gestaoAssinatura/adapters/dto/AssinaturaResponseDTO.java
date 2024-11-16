package com.example.tf.gestaoAssinatura.adapters.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor

public class AssinaturaResponseDTO {
    private long idSubscription;
    private long idClient;
    private long idApp;
    private Date date;
    private Date endDate;
    private String status;
}
