package com.example.tf.gestaoAssinatura.adapters.repository.IRepositories;

import com.example.tf.gestaoAssinatura.domain.model.PagamentoModel;

public interface IPagamentoRepository {
    PagamentoModel save(PagamentoModel payment);
}
