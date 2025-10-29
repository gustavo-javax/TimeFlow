package com.timeflow.timeflow.controller;

import com.timeflow.timeflow.dto.RegistroPontoDTO;
import com.timeflow.timeflow.mapper.RegistroPontoMapper;
import com.timeflow.timeflow.model.Funcionario;
import com.timeflow.timeflow.model.RegistroPonto;
import com.timeflow.timeflow.service.FuncionarioService;
import com.timeflow.timeflow.service.RegistroPontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registroponto")
@RequiredArgsConstructor

public class RegistroPontoController {

    private final RegistroPontoService registroPontoService;
    private final FuncionarioService funcionarioService;
    private final RegistroPontoMapper registroPontoMapper;

    @PostMapping
    public ResponseEntity<RegistroPontoDTO.RegistroPontoResponseDTO> registrar
            (@RequestBody RegistroPontoDTO.RegistroPontoRequestDTO dto) {
        Funcionario funcionario = funcionarioService.buscarPorId(dto.funcionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));

        RegistroPonto registro = registroPontoMapper.toEntity(dto);
        registro.setFuncionario(funcionario);

        RegistroPonto salvo = registroPontoService.registrarPonto(registro);
        return ResponseEntity.ok(registroPontoMapper.toDTO(salvo));
    }

    // READ ALL por funcionário
    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<RegistroPontoDTO.RegistroPontoResponseDTO>> listarPorFuncionario
    (@PathVariable Long funcionarioId) {
        List<RegistroPonto> registros = registroPontoService.listarPorFuncionario(funcionarioId);
        List<RegistroPontoDTO.RegistroPontoResponseDTO> lista = registros.stream()
                .map(registroPontoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<RegistroPontoDTO.RegistroPontoResponseDTO> buscarPorId(@PathVariable Long id) {
        RegistroPonto registro = registroPontoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("RegistroPonto não encontrado"));
        return ResponseEntity.ok(registroPontoMapper.toDTO(registro));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        registroPontoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
