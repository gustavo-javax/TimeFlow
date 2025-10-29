package com.timeflow.timeflow.dto;

import lombok.Builder;

public class EmpresaDTO {

    @Builder
    public record EmpresaRequestDTO(
            String nome,
            String cnpj,
            String senha,
            String email
    ) {}
    @Builder
    public record EmpresaResponseDTO(
            Long id,
            String nome,
            String cnpj,
            String email,
            String codigoDaEmpresa
    ) {}
}
