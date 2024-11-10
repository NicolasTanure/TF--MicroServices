package com.example.tf.gestaoAssinatura.adapters.repository.ITFRepositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Pagamento;

public interface PagamentoRepository extends ListCrudRepository<Pagamento, Long> {
    
}

