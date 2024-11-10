package com.example.tf.gestaoAssinatura.application.service;

import com.example.tf.gestaoAssinatura.adapters.repository.IRepositories.IAplicativoRepository;
import com.example.tf.gestaoAssinatura.domain.model.AplicativoModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AplicativoService {

    private final IAplicativoRepository aplicativoRepository;

    public AplicativoService(IAplicativoRepository aplicativoRepository) {
        this.aplicativoRepository = aplicativoRepository;
    }


    public List<AplicativoModel> listarAplicativos() {
        return aplicativoRepository.findAll();
    }

    public AplicativoModel atualizarCustoMensal(Long idAplicativo, Double novoCusto) {
        Optional<AplicativoModel> aplicativoOpt = aplicativoRepository.findById(idAplicativo);
        if (aplicativoOpt.isPresent()) {
            AplicativoModel aplicativoModel = aplicativoOpt.get();
            aplicativoModel.setCustoMensal(novoCusto);
            return aplicativoRepository.save(aplicativoModel);
        } else {
            throw new RuntimeException("Aplicativo não encontrado");
        }
    }
}
