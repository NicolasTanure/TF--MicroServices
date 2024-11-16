package com.example.tf.gestaoAssinatura.domain.IRepositories;

import java.util.List;
import java.util.Optional;

import com.example.tf.gestaoAssinatura.domain.model.AplicativoModel;

public interface IAplicativoRepository {
    AplicativoModel save(AplicativoModel appModel);

    List<AplicativoModel> findAll();

    Optional<AplicativoModel> findById(Long idAplicativo);
}
