package com.timeflow.timeflow.controller;

import com.timeflow.timeflow.dto.FuncionarioDTO;
import com.timeflow.timeflow.service.BancoHorasService;
import com.timeflow.timeflow.service.FuncionarioService;
import com.timeflow.timeflow.service.RegistroPontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor

public class DashboardController {

    private final FuncionarioService funcionarioService;
    private final RegistroPontoService registroPontoService;
    private final BancoHorasService bancoHorasService;

    @GetMapping("/empresa/{codigoDaEmpresa}/total")
    public ResponseEntity<Long> getTotalFuncionarios(@PathVariable String codigoDaEmpresa) {
        long total = funcionarioService.contarPorEmpresa(codigoDaEmpresa);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/empresa/{codigoDaEmpresa}/listarfuncionarios")
    public ResponseEntity<List<FuncionarioDTO.FuncionarioResponseDTO>> listarFuncionarios(
            @PathVariable String codigoDaEmpresa) {

        List<FuncionarioDTO.FuncionarioResponseDTO> funcionarios = funcionarioService.listarPorEmpresa(codigoDaEmpresa);
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/empresa/{codigoDaEmpresa}/funcionarios")
    public ResponseEntity<List<FuncionarioDTO.FuncionarioResponseDTO>> listarFuncionariosPorEmpresa(
            @PathVariable String codigoDaEmpresa
    ) {
        List<FuncionarioDTO.FuncionarioResponseDTO> funcionarios =
                funcionarioService.listarPorEmpresa(codigoDaEmpresa);
        return ResponseEntity.ok(funcionarios);
    }

}
