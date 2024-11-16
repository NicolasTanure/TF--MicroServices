package com.example.tf.gestaoAssinatura.adapters.repository.ImpRespositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Aplicativo;
import com.example.tf.gestaoAssinatura.adapters.repository.ITFRepositories.AplicativoRepository;
import com.example.tf.gestaoAssinatura.domain.IRepositories.IAplicativoRepository;
import com.example.tf.gestaoAssinatura.domain.model.AplicativoModel;

@Repository
public class AplicativoRepositoryJPA implements IAplicativoRepository {
    
    private AplicativoRepository appRepo;

    @Autowired
    public AplicativoRepositoryJPA(AplicativoRepository appRepo) {
        this.appRepo = appRepo;
    }

    @Override
    public AplicativoModel save(AplicativoModel appModel) {
        //Aplicativo newApp = appRepo.findByNome(appModel.getNome());
        Aplicativo newApp = Aplicativo.fromAplicativoModel(appModel);
        appRepo.save(newApp);
        return appModel;
    }

    @Override
    public List<AplicativoModel> findAll() {
        List<Aplicativo> apps = appRepo.findAll();

        return apps.stream()
                .map(it -> Aplicativo.toAplicativoModel(it))
                .toList();
    }

    @Override
    public Optional<AplicativoModel> findById(Long id) {
        Optional<Aplicativo> newApp = appRepo.findById(id);

        if (newApp.isPresent()) {
            return Optional.of(Aplicativo.toAplicativoModel(newApp.get()));
        } else {
            return Optional.empty();
        }
    }
}
