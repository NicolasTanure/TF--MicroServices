package com.example.tf.gestaoAssinatura.application.service;



import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Cliente;
import com.example.tf.gestaoAssinatura.adapters.repository.ITFRepositories.ClienteRepository;
import com.example.tf.gestaoAssinatura.domain.IRepositories.IClienteRepository;
import com.example.tf.gestaoAssinatura.domain.model.ClienteModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public List<ClienteModel> listarClientes() {
        return clienteRepository.findAll();
    }



}

