package com.timeflow.timeflow.dto;

import lombok.Builder;

public class FuncionarioDTO {

    @Builder
    public record FuncionarioRequestDTO(
            String nome,
            String cargo

    ) {}

    @Builder
    public record FuncionarioResponseDTO(
            Long id,
            String nome,
            String cargo,
            String codigoDeIdentificacao,
            Boolean ativo,
            String empresaNome,
            Long empresaId,
            String codigoDaEmpresa

    ) {}
}
