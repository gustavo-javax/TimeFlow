package com.timeflow.timeflow.controller;

import com.timeflow.timeflow.dto.BancoHorasDTO;
import com.timeflow.timeflow.mapper.BancoHorasMapper;
import com.timeflow.timeflow.model.BancoHoras;
import com.timeflow.timeflow.model.Funcionario;
import com.timeflow.timeflow.service.BancoHorasService;
import com.timeflow.timeflow.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bancohoras")
@RequiredArgsConstructor

public class BancoHorasController {
    private final BancoHorasService bancoHorasService;
    private final FuncionarioService funcionarioService;
    private final BancoHorasMapper bancoHorasMapper;

    @PostMapping
    public ResponseEntity<BancoHorasDTO.BancoHorasResponseDTO> criar(@RequestBody BancoHorasDTO.BancoHorasRequestDTO dto) {
        Funcionario funcionario = funcionarioService.buscarPorId(dto.funcionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));

        BancoHoras bancoHoras = bancoHorasMapper.toEntity(dto);
        bancoHoras.setFuncionario(funcionario);

        BancoHoras salvo = bancoHorasService.salvar(bancoHoras);
        return ResponseEntity.ok(bancoHorasMapper.toDTO(salvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoHorasDTO.BancoHorasResponseDTO> buscarPorId(@PathVariable Long id) {
        BancoHoras banco = bancoHorasService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Banco de horas não encontrado"));
        return ResponseEntity.ok(bancoHorasMapper.toDTO(banco));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<BancoHorasDTO.BancoHorasResponseDTO> buscarPorFuncionarioId(@PathVariable Long funcionarioId) {
        BancoHoras banco = bancoHorasService.buscarPorFuncionarioId(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Banco de horas não encontrado para o funcionário"));
        return ResponseEntity.ok(bancoHorasMapper.toDTO(banco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BancoHorasDTO.BancoHorasResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody BancoHorasDTO.BancoHorasRequestDTO dto) {

        BancoHoras bancoHorasExistente = bancoHorasService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("BancoHoras não encontrado"));

        Funcionario funcionario = funcionarioService.buscarPorId(dto.funcionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));

        bancoHorasExistente.setFuncionario(funcionario);
        bancoHorasExistente.setHorasTrabalhadas(dto.horasTrabalhadas());
        bancoHorasExistente.setHorasPrevistas(dto.horasPrevistas());

        if (dto.mesReferencia() != null && !dto.mesReferencia().isEmpty()) {
            bancoHorasExistente.setMesReferencia(YearMonth.parse(dto.mesReferencia()));
        }

        double saldo = bancoHorasExistente.getHorasTrabalhadas() - bancoHorasExistente.getHorasPrevistas();
        bancoHorasExistente.setSaldo(saldo);

        BancoHoras atualizado = bancoHorasService.atualizar(bancoHorasExistente);
        return ResponseEntity.ok(bancoHorasMapper.toDTO(atualizado));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        bancoHorasService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
