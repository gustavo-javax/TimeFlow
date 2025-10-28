package com.timeflow.timeflow.service;

import com.timeflow.timeflow.model.Empresa;
import com.timeflow.timeflow.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public Empresa salvar(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    public List<Empresa> listarTodas(){
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarEmpresaPorId(Long id){
        return empresaRepository.findById(id);
    }

    public Optional<Empresa> buscarEmpresaPorCodigo(String codigo){
        return empresaRepository.findByCodigoDaEmpresa(codigo);
    }

    public Empresa atualizar(Long id, Empresa empresaAtualizada) {
        return empresaRepository.findById(id).map(emp -> {
            if (empresaAtualizada.getNome() != null && !empresaAtualizada.getNome().trim().isEmpty()) {
                emp.setNome(empresaAtualizada.getNome());
            }
            if (empresaAtualizada.getCnpj() != null && !empresaAtualizada.getCnpj().trim().isEmpty()) {
                emp.setCnpj(empresaAtualizada.getCnpj());
            }
            if (empresaAtualizada.getSenha() != null && !empresaAtualizada.getSenha().trim().isEmpty()) {
                emp.setSenha(empresaAtualizada.getSenha());
            }
            if (empresaAtualizada.getEmail() != null && !empresaAtualizada.getEmail().trim().isEmpty()) {
                emp.setEmail(empresaAtualizada.getEmail());
            }
            if (empresaAtualizada.getCodigoDaEmpresa() != null && !empresaAtualizada.getCodigoDaEmpresa().trim().isEmpty()) {
                emp.setCodigoDaEmpresa(empresaAtualizada.getCodigoDaEmpresa());
            }
            return empresaRepository.save(emp);
        }).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    public void deletar(Long id) {
        if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("Empresa não encontrada");
        }
        empresaRepository.deleteById(id);
    }

}
