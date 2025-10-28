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
    public Optional<Funcionario> buscarPorCodigo(String codigo){
        return funcionarioRepository.findByCodigoDeIdentificacao(codigo);
    }




}
