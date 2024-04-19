package com.example.interdisciplinar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRecordDTO(
        @NotBlank String cep,
//        @NotBlank String rua,
        @NotBlank String complemento
//        @NotBlank String bairro,
//        @NotBlank String cidade

) {}
