package com.example.interdisciplinar.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRecordDTO(@NotBlank int numero, @NotBlank String senha) {
}
