package com.example.tf.gestaoAssinatura.adapters.repository.IRepositories;

import java.util.List;
import java.util.Optional;

import com.example.tf.gestaoAssinatura.domain.model.ClienteModel;

public interface IClienteRepository {
    List<ClienteModel> findAll();

    ClienteModel save(ClienteModel clientModel);

    Optional<ClienteModel> findById(Long clientId);
}
