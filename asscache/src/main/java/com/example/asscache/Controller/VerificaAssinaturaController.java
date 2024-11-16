package com.example.asscache.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.asscache.Service.AssinaturaClientService;

@RestController
@RequestMapping("/verificar-assinatura")
public class VerificaAssinaturaController {

    private final AssinaturaClientService assinaturaClientService;

    @Autowired
    public VerificaAssinaturaController(AssinaturaClientService assinaturaClientService) {
        this.assinaturaClientService = assinaturaClientService;
    }

    @GetMapping("/{codass}")
    public boolean verificar(@PathVariable Long codass) {
        return assinaturaClientService.isAssinaturaValida(codass);
    }
}
