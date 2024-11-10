package com.example.tf.gestaoAssinatura.adapters.repository.ImpRespositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tf.gestaoAssinatura.adapters.repository.Entities.Assinatura;
import com.example.tf.gestaoAssinatura.adapters.repository.IRepositories.IAssinaturaRepository;
import com.example.tf.gestaoAssinatura.adapters.repository.ITFRepositories.AssinaturaRepository;
import com.example.tf.gestaoAssinatura.domain.model.AssinaturaModel;

@Repository
public class AssinaturaRepositoryJPA implements IAssinaturaRepository{
    private AssinaturaRepository assiRepo;

    @Autowired
    public AssinaturaRepositoryJPA(AssinaturaRepository assiRepo) {
        this.assiRepo = assiRepo;
    }

    @Override
    public Optional<AssinaturaModel> findById(Long id) {
        Optional<Assinatura> novaAssinatura = assiRepo.findById(id);

        if (novaAssinatura.isPresent()) {
            return Optional.of(Assinatura.toSubscriptionModel(novaAssinatura.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public AssinaturaModel save(AssinaturaModel assinatura) {
        Assinatura novaAssinatura = Assinatura.fromSubscriptionModel(assinatura);
        assiRepo.save(novaAssinatura);
        return assinatura;
    }

    @Override
    public List<AssinaturaModel> findAll() {
        List<Assinatura> assinaturas = assiRepo.findAll();

        return assinaturas.stream()
                .map(it -> Assinatura.toSubscriptionModel(it))
                .toList();
    }

    @Override
    public List<AssinaturaModel> findByFimVigenciaAfter(LocalDate hoje) {
        List<Assinatura> assinaturas = assiRepo.findByFimVigenciaAfter(hoje);

        return assinaturas.stream()
                .map(it -> Assinatura.toSubscriptionModel(it))
                .toList();
    }

    @Override
    public List<AssinaturaModel> findByFimVigenciaBefore(LocalDate hoje) {
        List<Assinatura> assinaturas = assiRepo.findByFimVigenciaBefore(hoje);

        return assinaturas.stream()
                .map(it -> Assinatura.toSubscriptionModel(it))
                .toList();
    }

    @Override
    public List<AssinaturaModel> findByClienteCodigo(Long clienteId) {
        List<Assinatura> assinaturas = assiRepo.findByClienteCodigo(clienteId);

        return assinaturas.stream()
                .map(it -> Assinatura.toSubscriptionModel(it))
                .toList();
    }

    @Override
    public List<AssinaturaModel> findByAplicativoCodigo(Long aplicativoId) {
        List<Assinatura> assinaturas = assiRepo.findByAplicativoCodigo(aplicativoId);

        return assinaturas.stream()
                .map(it -> Assinatura.toSubscriptionModel(it))
                .toList();
    }
}
