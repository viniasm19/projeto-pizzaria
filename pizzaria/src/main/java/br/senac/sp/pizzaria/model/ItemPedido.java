package br.senac.sp.pizzaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Pedido pedido;
    private int qtd;
    @ManyToOne
    private Pizza pizza;
    private double preco;

    public double getTotal(){
        return qtd * preco;
    }

    public String toString(){
        return String.format("%d - %s - %3.2f",this.qtd, this.pizza.getSabor(), this.getTotal());
    }
}
