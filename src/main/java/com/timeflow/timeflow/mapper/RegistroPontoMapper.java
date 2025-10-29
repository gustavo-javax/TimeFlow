package com.timeflow.timeflow.mapper;

import com.timeflow.timeflow.dto.RegistroPontoDTO;
import com.timeflow.timeflow.model.RegistroPonto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroPontoMapper {

    @Mapping(target = "funcionario", ignore = true)
    @Mapping(target = "dataHora", ignore = true)
    RegistroPonto toEntity(RegistroPontoDTO.RegistroPontoRequestDTO dto);

    @Mapping(source = "funcionario.nome", target = "nomeFuncionario")
    @Mapping(source = "funcionario.codigoDeIdentificacao", target = "codigoFuncionario")
    @Mapping(source = "funcionario.empresa.codigoDaEmpresa", target = "codigoDaEmpresa")
    RegistroPontoDTO.RegistroPontoResponseDTO toDTO(RegistroPonto registroPonto);
}
