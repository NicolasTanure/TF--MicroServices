package com.example.tf.gestaoAssinatura.domain.IRepositories;

import com.example.tf.gestaoAssinatura.domain.model.PagamentoModel;

public interface IPagamentoRepository {
    PagamentoModel save(PagamentoModel payment);
}
