package com.example.interdisciplinar.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginModel {
    @NotBlank
    private int numero;

    @NotBlank
    private String senha;

    // Getters e setters


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
