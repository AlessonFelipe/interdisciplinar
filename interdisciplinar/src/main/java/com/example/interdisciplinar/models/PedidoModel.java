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

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private AddressModel endereco;
    @Column(name = "preco_total")
    private BigDecimal precoTotal;

    @Column(name = "forma_pagamento")
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    @ElementCollection
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

    @ElementCollection
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

    private int quantidade;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
