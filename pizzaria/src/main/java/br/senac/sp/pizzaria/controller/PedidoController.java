package br.senac.sp.pizzaria.controller;

import br.senac.sp.pizzaria.model.*;
import br.senac.sp.pizzaria.service.PedidoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.management.StandardEmitterMBean;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PedidoController implements Initializable {
    @FXML
    private TextField tfTelefone, tfCliente, tfEndereco, tfObservacao, tfIngredientes;
    @FXML
    private ComboBox<MeioPagamento> cbFormaPagamento;
    @FXML
    private ListView<ItemPedido> lstItem;
    @FXML
    private ListView<Pizza> lstPizza;
    @FXML
    private CheckBox chkRetira;
    @FXML
    private Spinner<Integer> spQtd;
    @FXML
    private Label lbTotal;

    private PedidoService service = new PedidoService();
    private Cliente cliente;
    private ObservableList<ItemPedido> itens;
    private ObservableList<Pizza> pizzas;
    private List<ItemPedido> itensPedidos = new ArrayList<>();

    public void buscarCliente(){
        if(!tfTelefone.getText().isBlank()){
            cliente = service.buscarCliente(tfTelefone.getText());
            if(cliente != null){
                tfCliente.setText(cliente.getNome());
                tfEndereco.setText(cliente.getEndereco());
            }else{
                tfCliente.setText("Cliente não encontrado");
                tfEndereco.setText("");
            }
        }
    }

    public void addItem(){
        int qtd = spQtd.getValue();
        Pizza pizza = lstPizza.getSelectionModel().getSelectedItem();
        if(pizza != null){
            ItemPedido item = new ItemPedido();
            item.setQtd(qtd);
            item.setPizza(pizza);
            item.setPreco(pizza.getPreco());
            itens.add(item);
        }
        atualizaTotal();
    }

    private void atualizaTotal(){
        double total = 0;
        for(ItemPedido item : itens){
            total += item.getTotal();
        }
        lbTotal.setText(String.format("R$ %5.2f", total));
    }

    public void removeItem(){
        ItemPedido item = lstItem.getSelectionModel().getSelectedItem();
        if(item != null){
            itens.remove(item);
            atualizaTotal();
        }
    }

    public void gerarPedido(){
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setItens(itens);
        pedido.setMeioPagamento(cbFormaPagamento.getValue());
        pedido.setObservacao(tfObservacao.getText());
        pedido.setRetira(chkRetira.isSelected());
        service.inserirPedido(pedido);
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Pedido gerado com o código: "+pedido.getCodigo());
        alerta.setHeaderText("Sucesso");
        alerta.show();
        limpar();
    }

    private void limpar(){
        cliente = null;
        tfCliente.clear();
        tfEndereco.clear();
        tfIngredientes.clear();
        tfTelefone.clear();
        itens.clear();
        lbTotal.setText("R$ 0,00");
        lstPizza.getSelectionModel().clearSelection();
        tfObservacao.clear();
        chkRetira.setSelected(false);
        cbFormaPagamento.setValue(null);
        tfTelefone.requestFocus();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbFormaPagamento.getItems().setAll(MeioPagamento.values());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1);
        spQtd.setValueFactory(valueFactory);
        buscarPizza();
        itens = FXCollections.observableArrayList(itensPedidos);
        lstItem.setItems(itens);
    }

    public void buscarPizza(){
        String ingrediente = tfIngredientes.getText();
        pizzas = FXCollections.observableArrayList(service.buscarPizza(ingrediente));
        lstPizza.setItems(pizzas);
    }
}
