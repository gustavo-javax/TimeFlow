package com.timeflow.timeflow.service;

import com.timeflow.timeflow.dto.FuncionarioDTO;
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

    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        if (funcionario.getCodigoDeIdentificacao() == null || funcionario.getCodigoDeIdentificacao().isEmpty()) {
            String codigo = "FUNC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            funcionario.setCodigoDeIdentificacao(codigo);
        }

        funcionario.setAtivo(true);

        Funcionario salvo = funcionarioRepository.save(funcionario);

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

    @Transactional
    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id).map(func -> {
            func.setNome(funcionarioAtualizado.getNome());
            func.setCargo(funcionarioAtualizado.getCargo());
            func.setCodigoDeIdentificacao(funcionarioAtualizado.getCodigoDeIdentificacao());
            func.setAtivo(funcionarioAtualizado.getAtivo());
            func.setEmpresa(funcionarioAtualizado.getEmpresa());
            return funcionarioRepository.save(func);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public void deletar(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }

    public long contarPorEmpresa(String codigoDaEmpresa) {
        return funcionarioRepository.findByEmpresa_CodigoDaEmpresa(codigoDaEmpresa).size();
    }

    public List<FuncionarioDTO.FuncionarioResponseDTO> listarPorEmpresa(String codigoDaEmpresa) {
        List<Funcionario> funcionarios = funcionarioRepository.findByEmpresa_CodigoDaEmpresa(codigoDaEmpresa);

        return funcionarios.stream().map(funcionario -> {
            Optional<BancoHoras> optionalBanco = bancoHorasRepository.findTopByFuncionarioOrderByMesReferenciaDesc(funcionario);

            Double horasTrabalhadas = optionalBanco.map(BancoHoras::getHorasTrabalhadas).orElse(0.0);
            Double saldo = optionalBanco.map(BancoHoras::getSaldo).orElse(0.0);
            String mesReferencia = optionalBanco.map(b -> b.getMesReferencia().toString()).orElse(null);

            return FuncionarioDTO.FuncionarioResponseDTO.builder()
                    .id(funcionario.getId())
                    .nome(funcionario.getNome())
                    .cargo(funcionario.getCargo())
                    .codigoDeIdentificacao(funcionario.getCodigoDeIdentificacao())
                    .ativo(funcionario.getAtivo())
                    .empresaNome(funcionario.getEmpresa() != null ? funcionario.getEmpresa().getNome() : null)
                    .empresaId(funcionario.getEmpresa() != null ? funcionario.getEmpresa().getId() : null)
                    .codigoDaEmpresa(funcionario.getEmpresa() != null ? funcionario.getEmpresa().getCodigoDaEmpresa() : null)
                    .horasTrabalhadas(horasTrabalhadas)
                    .saldo(saldo)
                    .mesReferencia(mesReferencia)
                    .build();
        }).toList();
    }



}
