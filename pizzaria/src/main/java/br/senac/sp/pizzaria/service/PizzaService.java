package br.senac.sp.pizzaria.service;

import br.senac.sp.pizzaria.dao.ClienteDao;
import br.senac.sp.pizzaria.dao.InterfaceDao;
import br.senac.sp.pizzaria.model.Cliente;
import br.senac.sp.pizzaria.model.Pizza;
import br.senac.sp.pizzaria.repository.PizzaRepository;

import java.util.List;

public class PizzaService {

    private final InterfaceDao<Pizza> dao = new PizzaRepository();

    public void salvar(Pizza pizza){
        if(pizza.getSabor().isBlank()){
            throw new RuntimeException("O sabor é obrigatório");
        }else if(pizza.getTipo() == null){
            throw new RuntimeException("O tipo é obrigatório");
        }else if(pizza.getTamanho() == null){
            throw new RuntimeException("O tamanho é obrigatório");
        }else if(pizza.getBorda() == null){
            throw new RuntimeException("A borda é obrigatória");
        }else{
            if(pizza.getId() == null) {
                dao.inserir(pizza);
            }else{
                dao.alterar(pizza);
            }
        }
    }

    public void excluir(Pizza pizza){
        dao.excluir(pizza.getId());
    }

    public List<Pizza> listar(){
        return dao.listar();
    }

    public List<Pizza> listar(String ingrediente){
        PizzaRepository daoP = (PizzaRepository) dao;
        return daoP.listar(ingrediente);
    }
}
