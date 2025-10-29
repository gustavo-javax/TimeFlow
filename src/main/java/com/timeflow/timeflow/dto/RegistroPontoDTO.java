package com.timeflow.timeflow.dto;

import com.timeflow.timeflow.model.enums.TipoRegistro;
import lombok.Builder;

import java.time.LocalDateTime;

public class RegistroPontoDTO {

    @Builder
    public record RegistroPontoRequestDTO(
            String codigoFuncionario,
            TipoRegistro tipoRegistro
    ) {}

    @Builder
    public record RegistroPontoResponseDTO(
            Long id,
            String nomeFuncionario,
            String codigoFuncionario,
            String codigoDaEmpresa,
            LocalDateTime dataHora,
            TipoRegistro tipoRegistro
    ) {}
}
