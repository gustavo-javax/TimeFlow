package com.timeflow.timeflow.service;

import com.timeflow.timeflow.model.BancoHoras;
import com.timeflow.timeflow.repository.BancoHorasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BancoHorasService {
    private final BancoHorasRepository bancoHorasRepository;

    public BancoHoras salvar(BancoHoras bancoHoras){
        return bancoHorasRepository.save(bancoHoras);
    }

    public Optional<BancoHoras> buscarPorId(Long id) {
        return bancoHorasRepository.findById(id);
    }

    public Optional<BancoHoras> buscarPorFuncionarioId(Long funcionarioId) {
        return bancoHorasRepository.findByFuncionarioId(funcionarioId);
    }

    public BancoHoras atualizar(BancoHoras bancoHoras) {
        return bancoHorasRepository.save(bancoHoras);
    }
    public void deletar(Long id) {
        if (!bancoHorasRepository.existsById(id)) {
            throw new RuntimeException("BancoHoras não encontrado");
        }
        bancoHorasRepository.deleteById(id);
    }

    public BancoHoras atualizarSaldo(BancoHoras bancoHoras, Double horasTrabalhadas) {
        bancoHoras.setHorasTrabalhadas(bancoHoras.getHorasTrabalhadas() + horasTrabalhadas);
        bancoHoras.setSaldo(bancoHoras.getHorasTrabalhadas() - bancoHoras.getHorasPrevistas());
        return bancoHorasRepository.save(bancoHoras);
    }
}
