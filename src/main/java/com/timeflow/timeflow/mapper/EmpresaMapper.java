package com.timeflow.timeflow.mapper;

import com.timeflow.timeflow.dto.EmpresaDTO;
import com.timeflow.timeflow.model.Empresa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    Empresa toEntity(EmpresaDTO.EmpresaRequestDTO dto);

    EmpresaDTO.EmpresaResponseDTO toDTO(Empresa empresa);

}
