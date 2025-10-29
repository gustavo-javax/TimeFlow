package com.timeflow.timeflow.service;

import com.timeflow.timeflow.model.Funcionario;
import com.timeflow.timeflow.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public Funcionario salvar(Funcionario funcionario){
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




}
