package com.example.interdisciplinar.models;


import jakarta.persistence.*;

import java.awt.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

@Entity
@Table(name="Cardapios")

public class CardapioModel implements Serializable {
    private static final long  serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idCardapio;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String imagemUrl;
    public String getImagemUrl() {
        return imagemUrl;
    }


    public void setImagemUrl(String imagemUrl) {
        try {
            new URL(imagemUrl).toURI();
            this.imagemUrl = imagemUrl;
        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalArgumentException("URL inválida: " + imagemUrl);
        }
    }

    public UUID getIdCardapio() {
        return idCardapio;
    }

    public void setIdCardapio(UUID idCardapio) {
        this.idCardapio = idCardapio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
