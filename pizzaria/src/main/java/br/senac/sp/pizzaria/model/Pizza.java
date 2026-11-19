package br.senac.sp.pizzaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_pizza", indexes = {
        @Index(name = "idx_sabor_tamanho", columnList = "sabor, tamanho, borda", unique = true)
})
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sabor;
    private TipoPizza tipo;
    private Tamanho tamanho;
    private Borda borda;
    private double preco;
    private String ingredientes;

    public String toString(){
        return String.format("%s - %s (%5.2f)", tamanho, sabor, preco);
    }
}
