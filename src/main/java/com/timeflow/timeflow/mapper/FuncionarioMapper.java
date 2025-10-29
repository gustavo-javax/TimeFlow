package com.timeflow.timeflow.mapper;

import com.timeflow.timeflow.dto.FuncionarioDTO;
import com.timeflow.timeflow.model.Funcionario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    FuncionarioDTO.FuncionarioResponseDTO toDTO(Funcionario funcionario);
    Funcionario toEntity(FuncionarioDTO.FuncionarioRequestDTO dto);

}
