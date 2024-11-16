package com.example.tf.gestaoAssinatura.domain.IRepositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.example.tf.gestaoAssinatura.domain.model.AssinaturaModel;

public interface IAssinaturaRepository {
        // Método para encontrar uma assinatura pelo ID
    Optional<AssinaturaModel> findById(Long id);

    // Método para salvar ou atualizar uma assinatura
    AssinaturaModel save(AssinaturaModel assinatura);

    // Método para listar todas as assinaturas
    List<AssinaturaModel> findAll();

    // Método para encontrar assinaturas ativas (com fim de vigência após a data atual)
    List<AssinaturaModel> findByFimVigenciaAfter(LocalDate hoje);

    // Método para encontrar assinaturas canceladas (com fim de vigência antes da data atual)
    List<AssinaturaModel> findByFimVigenciaBefore(LocalDate hoje);

    // Método para encontrar assinaturas por cliente
    List<AssinaturaModel> findByClienteCodigo(Long clienteId);

    // Método para encontrar assinaturas por aplicativo
    List<AssinaturaModel> findByAplicativoCodigo(Long aplicativoId);
}
