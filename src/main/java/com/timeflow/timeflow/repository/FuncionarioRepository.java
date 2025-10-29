package com.timeflow.timeflow.repository;

import com.timeflow.timeflow.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByCodigoDeIdentificacao(String codigo);
    boolean existsByCodigoDeIdentificacao(String codigoDeIdentificacao);
}
