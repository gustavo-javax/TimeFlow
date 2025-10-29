package com.timeflow.timeflow.controller;

import com.timeflow.timeflow.dto.FuncionarioDTO;
import com.timeflow.timeflow.mapper.FuncionarioMapper;
import com.timeflow.timeflow.model.Empresa;
import com.timeflow.timeflow.model.Funcionario;
import com.timeflow.timeflow.service.EmpresaService;
import com.timeflow.timeflow.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final FuncionarioMapper funcionarioMapper;
    private final EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<FuncionarioDTO.FuncionarioResponseDTO> criar(@RequestBody FuncionarioDTO.FuncionarioRequestDTO dto) {
        Empresa empresa = empresaService.buscarEmpresaPorId(dto.empresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        Funcionario funcionario = funcionarioMapper.toEntity(dto);
        funcionario.setEmpresa(empresa);

        Funcionario salvo = funcionarioService.salvar(funcionario);
        return ResponseEntity.ok(funcionarioMapper.toDTO(salvo));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO.FuncionarioResponseDTO>> listarTodos() {
        List<FuncionarioDTO.FuncionarioResponseDTO> lista = funcionarioService.listarTodos().stream()
                .map(funcionarioMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO.FuncionarioResponseDTO> buscarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        return ResponseEntity.ok(funcionarioMapper.toDTO(funcionario));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<FuncionarioDTO.FuncionarioResponseDTO> buscarPorCodigo(@PathVariable String codigo) {
        Funcionario funcionario = funcionarioService.buscarPorCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        return ResponseEntity.ok(funcionarioMapper.toDTO(funcionario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO.FuncionarioResponseDTO> atualizar(@PathVariable Long id,
                                                                           @RequestBody FuncionarioDTO.FuncionarioRequestDTO dto) {
        Empresa empresa = empresaService.buscarEmpresaPorId(dto.empresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        Funcionario funcionario = funcionarioMapper.toEntity(dto);
        funcionario.setEmpresa(empresa);

        Funcionario atualizado = funcionarioService.atualizar(id, funcionario);
        return ResponseEntity.ok(funcionarioMapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
