package com.timeflow.timeflow.repository;

import com.timeflow.timeflow.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
