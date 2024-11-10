package com.example.tf.gestaoAssinatura.adapters.repository.Entities;

import com.example.tf.gestaoAssinatura.domain.model.AssinaturaModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter



public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    public Assinatura(Long codigo, Cliente cliente, Aplicativo aplicativo, LocalDate inicioVigencia, LocalDate fimVigencia) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.aplicativo = aplicativo;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
    }
    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Aplicativo aplicativo;

    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;



    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Aplicativo getAplicativo() {
        return aplicativo;
    }

    public void setAplicativo(Aplicativo aplicativo) {
        this.aplicativo = aplicativo;
    }

    public LocalDate getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public LocalDate getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(LocalDate fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public static Assinatura fromSubscriptionModel(AssinaturaModel assinaturaModel) {
        return new Assinatura(assinaturaModel.getCodigo(),Cliente.fromClienteModel(assinaturaModel.getCliente()), Aplicativo.fromAplicativoModel(assinaturaModel.getAplicativo()),
                assinaturaModel.getInicioVigencia(), assinaturaModel.getFimVigencia());
    }

    public static AssinaturaModel toSubscriptionModel(Assinatura subscription) {
        return new AssinaturaModel(subscription.getCodigo(), Aplicativo.toAplicativoModel(subscription.getAplicativo()),
                Cliente.toClienteModel(subscription.getCliente()),
                subscription.getInicioVigencia(), subscription.getFimVigencia());
    }
}
