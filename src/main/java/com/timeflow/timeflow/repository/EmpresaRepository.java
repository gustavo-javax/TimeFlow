package com.timeflow.timeflow.repository;

import com.timeflow.timeflow.model.Empresa;
import com.timeflow.timeflow.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findByCodigoDaEmpresa(String codigo);
}
