package com.example.tf.gestaoAssinatura.adapters.repository.Entities;

import com.example.tf.gestaoAssinatura.domain.model.AplicativoModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Aplicativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;
    private Double custoMensal;


    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getCustoMensal() {
        return custoMensal;
    }

    public void setCustoMensal(Double custoMensal) {
        this.custoMensal = custoMensal;
    }

    public static Aplicativo fromAplicativoModel(AplicativoModel aplicativoModel) {
        return new Aplicativo(aplicativoModel.getCodigo(), aplicativoModel.getNome(), aplicativoModel.getCustoMensal());
    }

    public static AplicativoModel toAplicativoModel(Aplicativo app) {
        return new AplicativoModel(app.getCodigo(), app.getNome(), app.getCustoMensal());
    }
}




