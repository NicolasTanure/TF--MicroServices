package com.example.tf.gestaoAssinatura.adapters.repository.Entities;

import com.example.tf.gestaoAssinatura.domain.model.ClienteModel;

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

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;
    private String email;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Cliente fromClienteModel(ClienteModel clientModel) {
        return new Cliente(clientModel.getCodigo(), clientModel.getNome(), clientModel.getEmail());
    }

    public static ClienteModel toClienteModel(Cliente client) {
        return new ClienteModel(client.getCodigo(), client.getNome(), client.getEmail());
    }
}
