package com.timeflow.timeflow.service;

import com.timeflow.timeflow.model.RegistroPonto;
import com.timeflow.timeflow.model.enums.TipoRegistro;
import com.timeflow.timeflow.repository.RegistroPontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RegistroPontoService {
    private final RegistroPontoRepository registroPontoRepository;
    private final BancoHorasService bancoHorasService;

    public RegistroPonto registrarPonto(RegistroPonto registro) {
        RegistroPonto salvo = registroPontoRepository.save(registro);

        // Quando o tipo de registro for SAÍDA, calcula as horas trabalhadas no período
        if (registro.getTipoRegistro() == TipoRegistro.SAIDA) {
            Optional<RegistroPonto> ultimaEntrada = registroPontoRepository
                    .findTopByFuncionarioIdAndTipoRegistroOrderByDataHoraDesc(
                            registro.getFuncionario().getId(),
                            TipoRegistro.ENTRADA
                    );

            double horasTrabalhadasNoPeriodo = ultimaEntrada
                    .map(entrada -> Duration.between(entrada.getDataHora(), registro.getDataHora()).toMinutes() / 60.0)
                    .orElse(0.0);

            // Atualiza as horas no banco de horas do funcionário
            bancoHorasService.buscarPorFuncionarioId(registro.getFuncionario().getId())
                    .ifPresent(banco -> {
                        double novasHoras = banco.getHorasTrabalhadas() + horasTrabalhadasNoPeriodo;
                        banco.setHorasTrabalhadas(novasHoras);
                        banco.setSaldo(novasHoras - banco.getHorasPrevistas());
                        bancoHorasService.salvar(banco);
                    });
        }

        return salvo;
    }

    public List<RegistroPonto> listarPorFuncionario(Long funcionarioId) {
        return registroPontoRepository.findAllByFuncionarioId(funcionarioId);
    }
    public Optional<RegistroPonto> buscarPorId(Long id) {
        return registroPontoRepository.findById(id);
    }
    public void deletar(Long id) {
        if (!registroPontoRepository.existsById(id)) {
            throw new RuntimeException("RegistroPonto não encontrado");
        }
        registroPontoRepository.deleteById(id);
    }


}

