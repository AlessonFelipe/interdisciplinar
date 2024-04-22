package com.example.interdisciplinar.dtos;

import jakarta.validation.constraints.NotBlank;

public record AddressRecordDTO(
        @NotBlank String cep,
        @NotBlank String complemento

) {}
