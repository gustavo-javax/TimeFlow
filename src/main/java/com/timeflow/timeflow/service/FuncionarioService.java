package com.timeflow.timeflow.service;

import com.timeflow.timeflow.model.BancoHoras;
import com.timeflow.timeflow.model.Funcionario;
import com.timeflow.timeflow.repository.BancoHorasRepository;
import com.timeflow.timeflow.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final BancoHorasRepository bancoHorasRepository;

    public Funcionario salvar(Funcionario funcionario) {
        // Salva o funcionário primeiro
        Funcionario salvo = funcionarioRepository.save(funcionario);

        // Cria BancoHoras automaticamente vinculado ao funcionário
        bancoHorasRepository.save(
                BancoHoras.builder()
                        .funcionario(salvo)
                        .mesReferencia(java.time.YearMonth.now())
                        .horasTrabalhadas(0.0)
                        .horasPrevistas(44.0) // padrão de horas mensais
                        .saldo(0.0)
                        .build()
        );

        return salvo;
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Optional<Funcionario> buscarPorCodigo(String codigo) {
        return funcionarioRepository.findByCodigoDeIdentificacao(codigo);
    }

    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id).map(func -> {
            func.setNome(funcionarioAtualizado.getNome());
            func.setCargo(funcionarioAtualizado.getCargo());
            func.setCodigoDeIdentificacao(funcionarioAtualizado.getCodigoDeIdentificacao());
            func.setAtivo(funcionarioAtualizado.getAtivo());
            func.setEmpresa(funcionarioAtualizado.getEmpresa());
            return funcionarioRepository.save(func);
        }).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
    }

    public void deletar(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionario não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }



}
