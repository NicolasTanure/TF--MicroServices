package com.example.tf.gestaoAssinatura.application.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tf.gestaoAssinatura.adapters.dto.PaymentRequestDTO;
import com.example.tf.gestaoAssinatura.adapters.dto.RespostaPagamentoDTO;
import com.example.tf.gestaoAssinatura.domain.IRepositories.IAplicativoRepository;
import com.example.tf.gestaoAssinatura.domain.IRepositories.IAssinaturaRepository;
import com.example.tf.gestaoAssinatura.domain.IRepositories.IPagamentoRepository;
import com.example.tf.gestaoAssinatura.domain.model.AssinaturaModel;
import com.example.tf.gestaoAssinatura.domain.model.PagamentoModel;

import jakarta.transaction.Transactional;

@Service
public class PagamentoService {
    private IAssinaturaRepository assiRepo;
    private IPagamentoRepository pagaRepo;
    private IAplicativoRepository appRepo;
    
    @Autowired
    public PagamentoService(IAssinaturaRepository assiRepo,IPagamentoRepository pagaRepo, IAplicativoRepository appRepo) {
        this.assiRepo = assiRepo;
        this.pagaRepo = pagaRepo;
        this.appRepo = appRepo;

    }

    @Transactional
    //LocalDate dataPagamento, Long codigo, Double valor
    public RespostaPagamentoDTO fazPagamento(PaymentRequestDTO dto) {
        Long codigo = dto.getAssinaturaId();
        LocalDate dataPagamento = dto.getData();
        Double valor = dto.getValorPago();
        Optional<AssinaturaModel> assinaturaOpc = assiRepo.findById(codigo);
        if (assinaturaOpc.isEmpty()) {
            return RespostaPagamentoDTO.builder()
                    .data(dataPagamento)
                    .valor(valor)
                    .build();
        }

        AssinaturaModel assinatura = assinaturaOpc.get();
        Double valorDif = valor - assinatura.getAplicativo().getCustoMensal();

        if ( valorDif != 0) {
            return RespostaPagamentoDTO.builder()
                    .data(dataPagamento)
                    .valor(valor)
                    .build();
        }

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setDataPagamento(dataPagamento);
        pagamento.setAssinatura(assinatura);
        pagamento.setValorPago(valor);

        pagaRepo.save(pagamento);

        LocalDate fimVigenciaAtual = assinatura.getFimVigencia();

    LocalDate novaFimVigencia;
    if (fimVigenciaAtual.isBefore(dataPagamento)) {
        novaFimVigencia = dataPagamento.plusMonths(1);
        assinatura.setFimVigencia(novaFimVigencia);
        assinatura.setInicioVigencia(dataPagamento);
    } else {
        novaFimVigencia = fimVigenciaAtual.plusMonths(1);
        assinatura.setFimVigencia(novaFimVigencia);
    }
    appRepo.save(assinatura.getAplicativoModel());
    assiRepo.save(assinatura);

    // Retorna a resposta com status de pagamento bem-sucedido e a nova data de fim de vigência
    return RespostaPagamentoDTO.builder()
            .data(novaFimVigencia)
            .valor(valorDif) // Se precisar retornar o valor pago, mude isso
            .build();
    }
}
