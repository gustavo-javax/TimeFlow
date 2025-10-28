package com.timeflow.timeflow.dto;

import lombok.Builder;

public class BancoHorasDTO {
    @Builder
    public record BancoHorasRequestDTO(
            Long funcionarioId,
            String mesReferencia,
            Double horasTrabalhadas,
            Double horasPrevistas
    ) {}

    @Builder
    public record BancoHorasResponseDTO(
            Long id,
            Long funcionarioId,
            String funcionarioNome,
            String mesReferencia,
            Double horasTrabalhadas,
            Double horasPrevistas,
            Double saldo
    ) {}
}
