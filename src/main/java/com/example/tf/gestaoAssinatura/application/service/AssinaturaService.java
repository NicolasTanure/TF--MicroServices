package com.example.tf.gestaoAssinatura.application.service;

import com.example.tf.gestaoAssinatura.adapters.dto.ListarAssinaturasTipoDTO;
import com.example.tf.gestaoAssinatura.adapters.repository.IRepositories.IAssinaturaRepository;
import com.example.tf.gestaoAssinatura.domain.model.AssinaturaModel;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssinaturaService {

    private final IAssinaturaRepository assinaturaRepository;

    public AssinaturaService(IAssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    public Optional<AssinaturaModel> getAssinaturaById(Long id) {
        return assinaturaRepository.findById(id);
    }

    public AssinaturaModel updateAssinatura(AssinaturaModel assinatura) {
        return assinaturaRepository.save(assinatura);
    }

    public List<AssinaturaModel> getAssinaturasPorStatus(String status) {
        LocalDate hoje = LocalDate.now();
        List<AssinaturaModel> assinaturas;

        switch (status.toUpperCase()) {
            case "ATIVAS":
                assinaturas = assinaturaRepository.findByFimVigenciaAfter(hoje);
                break;
            case "CANCELADAS":
                assinaturas = assinaturaRepository.findByFimVigenciaBefore(hoje);
                break;
            case "TODAS":
            default:
                assinaturas = assinaturaRepository.findAll();
                break;
        }
        return assinaturas;
    }




    // Método para criar uma nova assinatura ou reativar uma assinatura existente
    public AssinaturaModel criarOuReativarAssinatura(AssinaturaModel novaAssinatura) {
        List<AssinaturaModel> assinaturasExistentes = assinaturaRepository
                .findByClienteCodigo(novaAssinatura.getCliente().getCodigo());

        AssinaturaModel assinatura;

        if (!assinaturasExistentes.isEmpty()) {
            // Supondo que você queira usar a primeira assinatura encontrada
            assinatura = assinaturasExistentes.get(0);
            // Renova a assinatura por mais 30 dias
            assinatura.setFimVigencia(assinatura.getFimVigencia().plusDays(30));
        } else {
            // Cria uma nova assinatura se não houver uma existente
            assinatura = novaAssinatura;
            assinatura.setInicioVigencia(LocalDate.now());
            assinatura.setFimVigencia(LocalDate.now().plusDays(7)); // Exemplo de 7 dias grátis
        }

        return assinaturaRepository.save(assinatura);
    }

    // Criar assinatura com 7 dias grátis
    public AssinaturaModel criarAssinatura(AssinaturaModel assinatura) {
        assinatura.setInicioVigencia(LocalDate.now());
        assinatura.setFimVigencia(LocalDate.now().plusDays(7));
        return assinaturaRepository.save(assinatura);
    }

    // Atualizar assinatura com o pagamento
    public void processarPagamento(Long assinaturaId, double valorPago, String promocao) {
        Optional<AssinaturaModel> assinaturaOpt = assinaturaRepository.findById(assinaturaId);

        if (assinaturaOpt.isPresent()) {
            AssinaturaModel assinatura = assinaturaOpt.get();

            LocalDate novaDataFim = calcularNovaDataValidade(assinatura, valorPago, promocao);
            assinatura.setFimVigencia(novaDataFim);

            assinaturaRepository.save(assinatura);
        } else {
            throw new RuntimeException("Assinatura não encontrada");
        }
    }

    // Lógica de cálculo de nova validade
    private LocalDate calcularNovaDataValidade(AssinaturaModel assinatura, double valorPago, String promocao) {
        LocalDate novaValidade;
        if ("PROMO_30_45".equals(promocao)) {
            novaValidade = assinatura.getFimVigencia().plusDays(45); // Promoção de 45 dias
        } else {
            novaValidade = assinatura.getFimVigencia().plusDays(30); // Plano básico
        }
        return novaValidade;
    }

    // Verificar validade da assinatura
    public boolean isAssinaturaValida(Long assinaturaId) {
        Optional<AssinaturaModel> assinaturaOpt = assinaturaRepository.findById(assinaturaId);
        return assinaturaOpt.map(assinatura -> assinatura.getFimVigencia().isAfter(LocalDate.now())).orElse(false);
    }

    // Listar todas as assinaturas
    public List<AssinaturaModel> listarAssinaturas() {
        return assinaturaRepository.findAll();
    }

    // Listar assinaturas por tipo (ATIVA ou CANCELADA)
    public ResponseEntity<List<ListarAssinaturasTipoDTO>> listarAssinaturasPorTipo(String tipo) {
        List<AssinaturaModel> lista;
        LocalDate hoje = LocalDate.now();
        if (tipo.equalsIgnoreCase("ATIVAS")) {
            lista =  assinaturaRepository.findByFimVigenciaAfter(hoje);
        } else if (tipo.equalsIgnoreCase("CANCELADAS")) {
            lista = assinaturaRepository.findByFimVigenciaBefore(hoje);
        }
        else{
            lista = listarAssinaturas(); // Se tipo for "TODAS"
        }
        
        List<ListarAssinaturasTipoDTO> listDTO = lista.stream()
                .map(assinatura -> new ListarAssinaturasTipoDTO(
                        assinatura.getCodigo(),
                        assinatura.getCliente().getCodigo(),
                        assinatura.getAplicativo().getCodigo(),
                        assinatura.getInicioVigencia(),
                        assinatura.getFimVigencia()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }

    public List<AssinaturaModel> listarAssinaturasPorCliente(Long clienteId) {
        return assinaturaRepository.findByClienteCodigo(clienteId);
    }

    // Listar assinaturas de um aplicativo
    public List<AssinaturaModel> listarAssinaturasPorAplicativo(Long aplicativoId) {
        return assinaturaRepository.findByAplicativoCodigo(aplicativoId);
    }
}
