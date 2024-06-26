package com.example.interdisciplinar.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="Usuarios")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idUsuario;
    private String nome;
    private int numero;
    private String senha;
    private String cep;
    private String complemento;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private AddressModel endereco;

    public AddressModel getEndereco() {
        return endereco;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
        if (endereco == null) {
            endereco = new AddressModel();
            endereco.setComplemento(complemento);
            endereco.setUser(this);
        } else {
            endereco.setComplemento(complemento);
        }
    }
    public void setCep(String cep) {
        this.cep = cep;
        if (endereco == null) {
            endereco = new AddressModel(cep);
            endereco.setUser(this);
        } else {
            endereco.setCep(cep);
        }
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        String numeroStr = String.valueOf(numero);
        if (numeroStr.length() == 9) {
            this.numero = numero;
        } else {
            throw new IllegalArgumentException("O número deve ter exatamente 9 caracteres.");
        }
    }

    public void setNome(String nome) {

        if (nome.length() > 5) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("O nome deve ter mais de 5 caracteres.");
        }
    }
}