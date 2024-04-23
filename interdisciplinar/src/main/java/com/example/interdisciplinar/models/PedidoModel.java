package com.example.interdisciplinar.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name="Pedidos")
public class PedidoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserModel usuario;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private CardapioModel produto;


    @Column(name = "frete")
    private String frete;

    public String getFrete() {
        return "Frete Grátis";
    }

 private BigDecimal precoTotal;

    @Column(name = "forma_pagamento")
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> carnes;

    public List<String> getCarnes() {
        return carnes;
    }

    public void setCarnes(List<String> carnes) {
        this.carnes = carnes;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<String> opcoes) {
        this.opcoes = opcoes;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> opcoes;

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }

    public CardapioModel getProduto() {
        return produto;
    }

    public void setProduto(CardapioModel produto) {
        this.produto = produto;
    }

}
