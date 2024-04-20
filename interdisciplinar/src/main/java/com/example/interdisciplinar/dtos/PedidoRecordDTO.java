package com.example.interdisciplinar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoRecordDTO( @NotBlank String usuario,@NotBlank String produto, @NotNull int quantidade) {}

