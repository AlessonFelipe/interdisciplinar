package com.example.interdisciplinar.models;

import jakarta.persistence.*;

import java.awt.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name="Usuarios")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idUsuario;
    private String nome;
    private int numero;
    private String senha;
    private String cep;
    private String complemento;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)

    private AddressModel endereco;

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

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
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
        // Validar se o nome tem mais de 5 caracteres
        if (nome.length() > 5) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("O nome deve ter mais de 5 caracteres.");
        }
    }



    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        // Validar a senha usando expressão regular
        String regex = "^(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        if (senha.matches(regex)) {
            this.senha = senha;
        } else {
            throw new IllegalArgumentException("A senha deve ter pelo menos 4 caracteres, incluindo pelo menos uma letra e um caractere especial.");
        }
    }
}