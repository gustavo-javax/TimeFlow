package com.timeflow.timeflow.service;

import com.timeflow.timeflow.model.Funcionario;
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

    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        if (funcionario.getCodigoDeIdentificacao() == null) {
            funcionario.setCodigoDeIdentificacao(gerarCodigoFuncionario());
        }
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarTodos(){
        return funcionarioRepository.findAll();
    }
    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Optional<Funcionario> buscarPorCodigo(String codigo){
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
    private String gerarCodigoFuncionario() {
        String codigo;
        do {
            codigo = "FUNC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (funcionarioRepository.existsByCodigoDeIdentificacao(codigo));
        return codigo;
    }



}
