package br.senac.sp.pizzaria.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (columnDefinition = "varchar(200)")
    private String nome;
    @Column(columnDefinition = "varchar(11)", unique = true)
    private String telefone;
    @Column(columnDefinition = "varchar(500)")
    private String endereco;
    private LocalDate aniversario;
}
