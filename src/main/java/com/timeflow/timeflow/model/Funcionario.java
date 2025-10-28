package com.timeflow.timeflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cargo;
    @Column(nullable = false, unique = true)
    private String codigoDeIdentificacao;

    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<RegistroPonto> registros;

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private BancoHoras bancoHoras;
}
