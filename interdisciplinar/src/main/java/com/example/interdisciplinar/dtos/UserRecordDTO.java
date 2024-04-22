package com.example.interdisciplinar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDTO(
        @NotBlank String nome,
        @NotNull int numero,
//        @NotBlank String senha,
        @NotBlank String cep,
        @NotBlank String complemento
) {}
