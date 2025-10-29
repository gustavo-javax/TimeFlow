package com.timeflow.timeflow.mapper;

import com.timeflow.timeflow.dto.FuncionarioDTO;
import com.timeflow.timeflow.model.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    // Mapeia entidade para DTO de resposta
    @Mapping(target = "empresaId", source = "empresa.id")
    @Mapping(target = "codigoDaEmpresa", source = "empresa.codigoDaEmpresa")
    @Mapping(target = "empresaNome", source = "empresa.nome")
    FuncionarioDTO.FuncionarioResponseDTO toDTO(Funcionario entity);

    // Mapeia DTO de requisição para entidade
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ativo", constant = "true")
    @Mapping(target = "empresa", ignore = true)
    @Mapping(target = "bancoHoras", ignore = true)
    @Mapping(target = "registros", ignore = true)
    Funcionario toEntity(FuncionarioDTO.FuncionarioRequestDTO dto);
}
