package com.example.tf.gestaoAssinatura.adapters.repository.Entities;

import com.example.tf.gestaoAssinatura.domain.model.PagamentoModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Builder
@Getter
@Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    private Assinatura assinatura;

    private Double valorPago;
    private LocalDate dataPagamento;
    private String promocao;



    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }

    public static Pagamento fromPaymentModel(PagamentoModel paymentModel) {
        return new Pagamento(paymentModel.getCodigo(), Assinatura.fromSubscriptionModel(paymentModel.getAssinatura()),
                paymentModel.getValorPago(), paymentModel.getDataPagamento(), paymentModel.getPromocao());
    }

    public static PagamentoModel toPaymentModel(Pagamento payment) {
        return new PagamentoModel(payment.getCodigo(), Assinatura.toSubscriptionModel(payment.getAssinatura()),
                payment.getValorPago(), payment.getDataPagamento(), payment.getPromocao());
    }

    }


