package com.timeflow.timeflow.controller;

import com.timeflow.timeflow.dto.EmpresaDTO;
import com.timeflow.timeflow.mapper.EmpresaMapper;
import com.timeflow.timeflow.model.Empresa;
import com.timeflow.timeflow.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor

public class EmpresaController {

    private final EmpresaService empresaService;
    private final EmpresaMapper empresaMapper;

    @PostMapping
    public ResponseEntity<EmpresaDTO.EmpresaResponseDTO> criar(@RequestBody EmpresaDTO.EmpresaRequestDTO dto) {
        Empresa empresa = empresaMapper.toEntity(dto);
        Empresa salvo = empresaService.salvar(empresa);
        return ResponseEntity.ok(empresaMapper.toDTO(salvo));
    }
    @GetMapping
    public ResponseEntity<List<EmpresaDTO.EmpresaResponseDTO>> listarTodas() {
        List<EmpresaDTO.EmpresaResponseDTO> lista = empresaService.listarTodas().stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO.EmpresaResponseDTO> buscarPorId(@PathVariable Long id) {
        Empresa empresa = empresaService.buscarEmpresaPorId(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return ResponseEntity.ok(empresaMapper.toDTO(empresa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO.EmpresaResponseDTO> atualizar(@PathVariable Long id,
                                                                   @RequestBody EmpresaDTO.EmpresaRequestDTO dto) {
        Empresa empresaAtualizada = empresaMapper.toEntity(dto);
        Empresa atualizado = empresaService.atualizar(id, empresaAtualizada);
        return ResponseEntity.ok(empresaMapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        empresaService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
