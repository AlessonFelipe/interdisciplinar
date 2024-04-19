package com.example.interdisciplinar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDTO(
        @NotBlank(message = "O nome não pode estar em branco") String nome,
        @NotNull(message = "O numero não pode estar em branco") int numero,
        @NotBlank(message = "A senha não pode estar em branco") String senha,
        @NotBlank String cep,
        @NotBlank String complemento
) {}
