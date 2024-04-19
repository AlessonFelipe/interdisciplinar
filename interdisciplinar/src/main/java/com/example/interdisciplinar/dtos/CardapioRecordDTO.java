package com.example.interdisciplinar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CardapioRecordDTO(@NotBlank String nome, @NotBlank String descricao, @NotNull BigDecimal preco, @NotBlank String imagemUrl) {
}
