package com.example.tf.gestaoAssinatura.application.service;

import com.example.tf.gestaoAssinatura.domain.IRepositories.IAplicativoRepository;
import com.example.tf.gestaoAssinatura.domain.model.AplicativoModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<AplicativoModel> atualizarCustoMensal(Long idAplicativo, Double novoCusto) {
        Optional<AplicativoModel> aplicativoOpt = aplicativoRepository.findById(idAplicativo);

        if (aplicativoOpt.isPresent()) {
            AplicativoModel aplicativoModel = aplicativoOpt.get();
            aplicativoModel.setCustoMensal(novoCusto);
            AplicativoModel aplicativoAtualizado = aplicativoRepository.save(aplicativoModel);
            return ResponseEntity.ok(aplicativoAtualizado); // Retorna 200 OK com o aplicativo atualizado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // Retorna 404 se não encontrar o aplicativo
                    .body(null);
        }
    }
}
