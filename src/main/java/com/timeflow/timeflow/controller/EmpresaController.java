package com.timeflow.timeflow.controller;

import com.timeflow.timeflow.dto.EmpresaDTO;
import com.timeflow.timeflow.model.Empresa;
import com.timeflow.timeflow.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor

public class EmpresaController {

    private final EmpresaService empresaService;


}
