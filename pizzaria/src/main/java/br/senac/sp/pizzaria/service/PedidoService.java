package br.senac.sp.pizzaria.service;

import br.senac.sp.pizzaria.model.Cliente;
import br.senac.sp.pizzaria.model.ItemPedido;
import br.senac.sp.pizzaria.model.Pedido;
import br.senac.sp.pizzaria.model.Pizza;
import br.senac.sp.pizzaria.repository.ClienteRepository;
import br.senac.sp.pizzaria.repository.PedidoRepository;
import br.senac.sp.pizzaria.repository.PizzaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoService {
    private final PizzaRepository repPizza = new PizzaRepository();
    private final ClienteRepository repCliente = new ClienteRepository();
    private final PedidoRepository repository = new PedidoRepository();

    public Cliente buscarCliente(String telefone){
        return repCliente.buscarTel(telefone);
    }

    public List<Pizza> buscarPizza(String ingrediente){
        return repPizza.listar(ingrediente);
    }

    public void inserirPedido(Pedido pedido){
        pedido.setData(LocalDateTime.now());
        pedido.setPrevisao(pedido.getData().plusMinutes(40));
        for(ItemPedido item : pedido.getItens()){
            item.setPedido(pedido);
        }
        repository.inserir(pedido);
    }
}
