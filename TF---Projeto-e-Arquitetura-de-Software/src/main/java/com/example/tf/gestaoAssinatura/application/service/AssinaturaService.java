package com.example.tf.gestaoAssinatura.application.service;

import com.example.tf.gestaoAssinatura.adapters.dto.AssinaturaRequestDTO;
import com.example.tf.gestaoAssinatura.adapters.dto.AssinaturaResponseDTO;
import com.example.tf.gestaoAssinatura.adapters.dto.ListarAssinaturasTipoDTO;
import com.example.tf.gestaoAssinatura.domain.IRepositories.IAplicativoRepository;
import com.example.tf.gestaoAssinatura.domain.IRepositories.IAssinaturaRepository;
import com.example.tf.gestaoAssinatura.domain.IRepositories.IClienteRepository;
import com.example.tf.gestaoAssinatura.domain.model.AplicativoModel;
import com.example.tf.gestaoAssinatura.domain.model.AssinaturaModel;

import com.example.tf.gestaoAssinatura.domain.model.ClienteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssinaturaService {

    private final IAssinaturaRepository assinaturaRepository;
    private final IClienteRepository clienteRepository;

    private final IAplicativoRepository aplicativoRepository;

    public AssinaturaService(IAssinaturaRepository assinaturaRepository, IClienteRepository clienteRepository, IAplicativoRepository aplicativoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.clienteRepository = clienteRepository;
        this.aplicativoRepository = aplicativoRepository;
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

    public AssinaturaResponseDTO criarAssinatura(AssinaturaRequestDTO assinaturaDTO) {
        Optional<ClienteModel> clienteOpt = clienteRepository.findById(assinaturaDTO.getCodigoCliente());
        Optional<AplicativoModel> aplicativoOpt = aplicativoRepository.findById(assinaturaDTO.getCodigoAplicativo());



        ClienteModel cliente = clienteOpt.get();
        AplicativoModel aplicativo = aplicativoOpt.get();

        AssinaturaModel assinatura = new AssinaturaModel();
        assinatura.setCliente(cliente);
        assinatura.setAplicativo(aplicativo);
        assinatura.setInicioVigencia(LocalDate.now());
        assinatura.setFimVigencia(LocalDate.now().plusDays(7)); // Exemplo de 7 dias grátis

        AssinaturaModel assinaturaSalva = assinaturaRepository.save(assinatura);

        return new AssinaturaResponseDTO(
                assinaturaSalva.getCodigo(),                           // Código da assinatura gerada
                assinaturaSalva.getCliente().getCodigo(),              // Código do cliente
                assinaturaSalva.getAplicativo().getCodigo(),           // Código do aplicativo
                Date.from(assinaturaSalva.getInicioVigencia().atStartOfDay(ZoneId.systemDefault()).toInstant()), // Data de início
                Date.from(assinaturaSalva.getFimVigencia().atStartOfDay(ZoneId.systemDefault()).toInstant()),      // Data de encerramento
                "ATIVA"  // Status da assinatura
        );
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

    public ResponseEntity<List<ListarAssinaturasTipoDTO>> listarAssinaturasPorCliente(Long clienteId) {
        List<AssinaturaModel> lista;
        lista = assinaturaRepository.findByClienteCodigo(clienteId);
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

    // Listar assinaturas de um aplicativo
    public ResponseEntity<List<ListarAssinaturasTipoDTO>> listarAssinaturasPorAplicativo(Long aplicativoId) {
        List<AssinaturaModel> lista;
        lista = assinaturaRepository.findByAplicativoCodigo(aplicativoId);
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
}
