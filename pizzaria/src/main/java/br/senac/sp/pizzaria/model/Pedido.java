package br.senac.sp.pizzaria.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tb_pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @ManyToOne
    private Cliente cliente;
    private LocalDateTime previsao;
    private LocalDateTime data;
    private MeioPagamento meioPagamento;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemPedido> itens;
    private String observacao;
    private boolean retira;

    public double getTotal(){
        double total = 0;
        for(ItemPedido item : itens){
            total += item.getTotal();
        }
        return total;
    }
}
