package com.example.tf.gestaoAssinatura.adapters.controllers;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Cliente;
import com.example.tf.gestaoAssinatura.application.service.ClienteService;

import com.example.tf.gestaoAssinatura.domain.model.ClienteModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servcad")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Listar todos os clientes
    @GetMapping("/clientes")
    public List<ClienteModel> listarClientes() {
        return clienteService.listarClientes();
    }
}

