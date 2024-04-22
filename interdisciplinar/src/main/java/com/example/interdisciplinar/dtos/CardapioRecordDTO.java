package com.example.interdisciplinar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CardapioRecordDTO(@NotBlank String nome, @NotBlank String descricao, @NotNull BigDecimal preco,
@NotEmpty List<String> opcoes, @NotEmpty List<String> carnes) {
}
