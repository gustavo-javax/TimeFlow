package com.timeflow.timeflow.dto;

import lombok.Builder;

public class FuncionarioDTO {

    @Builder
    public record FuncionarioRequestDTO(
            String nome,
            String cargo,
            String codigoDeIdentificacao,
            Boolean ativo,
            Long empresaId
    ) {}

    @Builder
    public record FuncionarioResponseDTO(
            Long id,
            String nome,
            String cargo,
            String codigoDeIdentificacao,
            Boolean ativo,
            String empresaNome
    ) {}
}
