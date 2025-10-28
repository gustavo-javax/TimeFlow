package com.timeflow.timeflow.repository;

import com.timeflow.timeflow.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
