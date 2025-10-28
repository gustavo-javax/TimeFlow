package com.timeflow.timeflow.dto;

import com.timeflow.timeflow.model.enums.TipoRegistro;
import lombok.Builder;

import java.time.LocalDateTime;

public class RegistroPontoDTO {

    @Builder
    public record RegistroPontoRequestDTO(
            Long funcionarioId,
            LocalDateTime dataHora,
            TipoRegistro tipoRegistro
    ) {}

    @Builder
    public record RegistroPontoResponseDTO(
            Long id,
            Long funcionarioId,
            String funcionarioNome,
            LocalDateTime dataHora,
            TipoRegistro tipoRegistro
    ) {}
}
