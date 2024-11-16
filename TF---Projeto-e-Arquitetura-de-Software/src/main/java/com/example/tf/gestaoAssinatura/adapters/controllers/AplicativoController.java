package com.example.tf.gestaoAssinatura.adapters.controllers;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Aplicativo;
import com.example.tf.gestaoAssinatura.application.service.AplicativoService;

import com.example.tf.gestaoAssinatura.domain.model.AplicativoModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servcad")
public class AplicativoController {

    private final AplicativoService aplicativoService;

    public AplicativoController(AplicativoService aplicativoService) {
        this.aplicativoService = aplicativoService;
    }

    // Listar todos os aplicativos
    @GetMapping("/aplicativos")
    public List<AplicativoModel> listarAplicativos() {
        return aplicativoService.listarAplicativos();
    }

    // Atualizar o custo mensal do aplicativo
    @PostMapping("/aplicativos/atualizacusto/{idAplicativo}")
    public ResponseEntity<?> atualizarCustoMensal(@PathVariable Long idAplicativo, @RequestBody Double custo) {
        ResponseEntity<AplicativoModel> response = aplicativoService.atualizarCustoMensal(idAplicativo, custo);
        return response;
    }
}
