package com.timeflow.timeflow.service;

import com.timeflow.timeflow.model.BancoHoras;
import com.timeflow.timeflow.model.Funcionario;
import com.timeflow.timeflow.model.RegistroPonto;
import com.timeflow.timeflow.repository.BancoHorasRepository;
import com.timeflow.timeflow.repository.RegistroPontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BancoHorasService {

    private final BancoHorasRepository bancoHorasRepository;
    private final RegistroPontoRepository registroPontoRepository;

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


    public void atualizarSaldoPorFuncionario(Funcionario funcionario) {
        BancoHoras banco = bancoHorasRepository.findByFuncionarioId(funcionario.getId())
                .orElseThrow(() -> new RuntimeException("Banco de horas não encontrado"));

        double totalHorasTrabalhadas = registroPontoRepository.findAllByFuncionarioId(funcionario.getId())
                .stream()
                .mapToDouble(r -> 1.0)
                .sum();

        banco.setHorasTrabalhadas(totalHorasTrabalhadas);
        banco.setSaldo(totalHorasTrabalhadas - banco.getHorasPrevistas());

        bancoHorasRepository.save(banco);
    }
}
