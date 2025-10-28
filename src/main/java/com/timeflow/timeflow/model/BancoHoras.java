package com.timeflow.timeflow.model;

import com.timeflow.timeflow.config.YearMonthConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "banco_horas")
public class BancoHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @Convert(converter = YearMonthConverter.class)
    private YearMonth mesReferencia;

    private Double horasTrabalhadas = 0.0;
    private Double horasPrevistas = 0.0;
    private Double saldo = 0.0;


}
