package com.timeflow.timeflow.mapper;

import com.timeflow.timeflow.dto.BancoHorasDTO;
import com.timeflow.timeflow.model.BancoHoras;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.YearMonth;

@Mapper(componentModel = "spring")
public interface BancoHorasMapper {

    @Mapping(source = "funcionario.id", target = "funcionarioId")
    @Mapping(source = "funcionario.nome", target = "funcionarioNome")
    @Mapping(source = "mesReferencia", target = "mesReferencia") // chama o método abaixo
    BancoHorasDTO.BancoHorasResponseDTO toDTO(BancoHoras bancoHoras);

    BancoHoras toEntity(BancoHorasDTO.BancoHorasRequestDTO dto);

    default String map(YearMonth value) {
        return value != null ? value.toString() : null;
    }

    default YearMonth map(String value) {
        return value != null ? YearMonth.parse(value) : null;
    }
}
