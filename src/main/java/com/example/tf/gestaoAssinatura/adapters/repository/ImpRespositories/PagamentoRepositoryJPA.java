package com.example.tf.gestaoAssinatura.adapters.repository.ImpRespositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Pagamento;
import com.example.tf.gestaoAssinatura.adapters.repository.IRepositories.IPagamentoRepository;
import com.example.tf.gestaoAssinatura.adapters.repository.ITFRepositories.PagamentoRepository;
import com.example.tf.gestaoAssinatura.domain.model.PagamentoModel;

@Repository
public class PagamentoRepositoryJPA implements IPagamentoRepository {
    private PagamentoRepository pagamentoRepo;

    @Autowired
    public PagamentoRepositoryJPA(PagamentoRepository pagamentoRepo) {
        this.pagamentoRepo = pagamentoRepo;
    }

    @Override
    public PagamentoModel save(PagamentoModel pagamento) {
        Pagamento novoPagamento = Pagamento.fromPaymentModel(pagamento);
        pagamentoRepo.save(novoPagamento);

        return pagamento;
    }
}
