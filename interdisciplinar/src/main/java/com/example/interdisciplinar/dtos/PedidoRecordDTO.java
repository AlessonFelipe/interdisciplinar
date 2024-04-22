package com.example.interdisciplinar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record PedidoRecordDTO(@NotBlank String usuario, @NotBlank String produto, @NotBlank String formaPagamento, @NotEmpty(message = "A lista de opções não pode estar vazia")
@Size(max = 9)
List<String> opcoes,@NotEmpty(message = "A lista de carnes não pode estar vazia")
@Size(max = 2)
List<String> carnes, BigDecimal precoTotal){}

