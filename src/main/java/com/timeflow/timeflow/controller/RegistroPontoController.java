package com.timeflow.timeflow.controller;

import com.timeflow.timeflow.dto.RegistroPontoDTO;
import com.timeflow.timeflow.mapper.RegistroPontoMapper;
import com.timeflow.timeflow.model.Funcionario;
import com.timeflow.timeflow.model.RegistroPonto;
import com.timeflow.timeflow.model.enums.TipoRegistro;
import com.timeflow.timeflow.service.FuncionarioService;
import com.timeflow.timeflow.service.RegistroPontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registroponto")
@RequiredArgsConstructor
public class RegistroPontoController {

    private final RegistroPontoService registroPontoService;
    private final FuncionarioService funcionarioService;
    private final RegistroPontoMapper registroPontoMapper;

    // CRIAR registro de ponto
    @PostMapping
    public ResponseEntity<RegistroPontoDTO.RegistroPontoResponseDTO> registrarPonto(
            @RequestParam String codigoFuncionario,
            @RequestParam TipoRegistro tipo) {

        Funcionario funcionario = funcionarioService.buscarPorCodigo(codigoFuncionario)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        RegistroPonto registro = RegistroPonto.builder()
                .funcionario(funcionario)
                .tipoRegistro(tipo)
                .dataHora(LocalDateTime.now())
                .build();

        RegistroPonto salvo = registroPontoService.registrarPonto(registro);
        return ResponseEntity.ok(registroPontoMapper.toDTO(salvo));
    }

    // LISTAR todos os registros de um funcionário
    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<RegistroPontoDTO.RegistroPontoResponseDTO>> listarPorFuncionario(
            @PathVariable Long funcionarioId) {

        List<RegistroPonto> registros = registroPontoService.listarPorFuncionario(funcionarioId);
        List<RegistroPontoDTO.RegistroPontoResponseDTO> lista = registros.stream()
                .map(registroPontoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // BUSCAR registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<RegistroPontoDTO.RegistroPontoResponseDTO> buscarPorId(@PathVariable Long id) {
        RegistroPonto registro = registroPontoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("RegistroPonto não encontrado"));
        return ResponseEntity.ok(registroPontoMapper.toDTO(registro));
    }

    // DELETAR registro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        registroPontoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
