package com.example.tf.gestaoAssinatura.adapters.repository.ImpRespositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Cliente;
import com.example.tf.gestaoAssinatura.adapters.repository.ITFRepositories.ClienteRepository;
import com.example.tf.gestaoAssinatura.domain.IRepositories.IClienteRepository;
import com.example.tf.gestaoAssinatura.domain.model.ClienteModel;

@Repository
public class ClienteRepositoryJPA implements IClienteRepository {
    private ClienteRepository cliRep;

    @Autowired
    public ClienteRepositoryJPA(ClienteRepository cliRep) {
        this.cliRep = cliRep;
    }

    @Override
    public List<ClienteModel> findAll() {
        List<Cliente> clients = cliRep.findAll();

        return clients.stream()
                .map(it -> Cliente.toClienteModel(it))
                .toList();
    }

    @Override
    public ClienteModel save(ClienteModel clientModel) {
        Cliente newClient = Cliente.fromClienteModel(clientModel);
        cliRep.save(newClient);

        return clientModel;
    }

    @Override
    public Optional<ClienteModel> findById(Long clientId) {
        Optional<Cliente> newClient = cliRep.findById(clientId);

        if (newClient.isPresent()) {
            return Optional.of(Cliente.toClienteModel(newClient.get()));
        } else {
            return Optional.empty();
        }
    }

}
