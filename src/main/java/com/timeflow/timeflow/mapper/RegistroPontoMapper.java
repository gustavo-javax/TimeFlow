package com.timeflow.timeflow.mapper;

import com.timeflow.timeflow.dto.RegistroPontoDTO;
import com.timeflow.timeflow.model.RegistroPonto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroPontoMapper {
    @Mapping(source = "funcionario.id", target = "funcionarioId")
    @Mapping(source = "funcionario.nome", target = "funcionarioNome")
    RegistroPontoDTO.RegistroPontoResponseDTO toDTO(RegistroPonto registro);

    RegistroPonto toEntity(RegistroPontoDTO.RegistroPontoRequestDTO dto);
}
