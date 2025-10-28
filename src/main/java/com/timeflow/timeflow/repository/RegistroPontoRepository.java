package com.timeflow.timeflow.repository;

import com.timeflow.timeflow.model.RegistroPonto;
import com.timeflow.timeflow.model.enums.TipoRegistro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {
    Optional<RegistroPonto> findTopByFuncionarioIdAndTipoRegistroOrderByDataHoraDesc(Long funcionarioId, TipoRegistro tipoRegistro);
    List<RegistroPonto> findAllByFuncionarioId(Long funcionarioId);

}
