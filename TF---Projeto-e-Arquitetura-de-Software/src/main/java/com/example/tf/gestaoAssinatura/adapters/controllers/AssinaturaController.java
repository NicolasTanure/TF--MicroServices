package com.example.tf.gestaoAssinatura.adapters.controllers;

import com.example.tf.gestaoAssinatura.adapters.dto.AssinaturaRequestDTO;
import com.example.tf.gestaoAssinatura.adapters.dto.AssinaturaResponseDTO;
import com.example.tf.gestaoAssinatura.adapters.dto.ListarAssinaturasTipoDTO;
import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Assinatura;
import com.example.tf.gestaoAssinatura.application.service.AssinaturaService;

import com.example.tf.gestaoAssinatura.domain.model.AssinaturaModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servcad")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    // Criar assinatura
    @PostMapping("/assinaturas")
    public AssinaturaResponseDTO criarAssinatura(@RequestBody AssinaturaRequestDTO assinatura) {
        return assinaturaService.criarAssinatura(assinatura);
    }

    // Listar assinaturas por tipo
    @GetMapping("/assinaturas/{tipo}")
    public ResponseEntity<List<ListarAssinaturasTipoDTO>>listarAssinaturas(@PathVariable String tipo) {
        return assinaturaService.listarAssinaturasPorTipo(tipo);
    }

    @PostMapping("/reativar")
    public AssinaturaModel criarOuReativarAssinatura(@RequestBody AssinaturaModel novaAssinatura) {
        return assinaturaService.criarOuReativarAssinatura(novaAssinatura);
    }


    @GetMapping("/assinvalida/{codass}")
    public boolean isAssinaturaValida(@PathVariable Long codass) {
        return assinaturaService.isAssinaturaValida(codass);
    }

    // Registrar pagamento
    @PostMapping("/registrarpagamento")
    public void processarPagamento(@RequestParam Long codass, @RequestParam double valorPago, @RequestParam String promocao) {
        assinaturaService.processarPagamento(codass, valorPago, promocao);
    }

    // Buscar assinatura por ID
    @GetMapping("/buscar/{id}")
    public Optional<AssinaturaModel> getAssinaturaById(@PathVariable Long id) {
        return assinaturaService.getAssinaturaById(id);
    }

    // **Atualizar assinatura**
    @PutMapping("/atualizar")
    public AssinaturaModel updateAssinatura(@RequestBody AssinaturaModel assinatura) {
        return assinaturaService.updateAssinatura(assinatura);
    }

    @GetMapping("/status/{status}")
    public List<AssinaturaModel> getAssinaturasPorStatus(@PathVariable String status) {
        return assinaturaService.getAssinaturasPorStatus(status);
    }

    @GetMapping("/asscli/{codcli}")
    public ResponseEntity<List<ListarAssinaturasTipoDTO>> listarAssinaturasCliente(@PathVariable Long codcli) {
        return assinaturaService.listarAssinaturasPorCliente(codcli);
    }

    // Listar assinaturas de um aplicativo
    @GetMapping("/assapp/{codapp}")
    public ResponseEntity<List<ListarAssinaturasTipoDTO>> listarAssinaturasAplicativo(@PathVariable Long codapp) {
        return assinaturaService.listarAssinaturasPorAplicativo(codapp);
    }
}
