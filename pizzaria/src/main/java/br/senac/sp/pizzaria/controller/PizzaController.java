package br.senac.sp.pizzaria.controller;

import br.senac.sp.pizzaria.model.*;
import br.senac.sp.pizzaria.service.PizzaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PizzaController implements Initializable {
    @FXML
    private TextField tfId, tfSabor, tfPreco;
    @FXML
    private TextArea taIngredientes;
    @FXML
    private ComboBox<Tamanho> cbTamanho;
    @FXML
    private ComboBox<TipoPizza> cbTipo;
    @FXML
    private ComboBox<Borda> cbBorda;
    @FXML
    private TableView<Pizza> tbPizza;
    @FXML
    private TableColumn<Pizza, Long> colId;
    @FXML
    private TableColumn<Pizza, String> colSabor;
    @FXML
    private TableColumn<Pizza, Tamanho> colTamanho;
    private ObservableList<Pizza> lista;
    private PizzaService service = new PizzaService();
    private Pizza pizzaSelecionada;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbBorda.getItems().setAll(Borda.values());
        cbTamanho.getItems().setAll(Tamanho.values());
        cbTipo.getItems().setAll(TipoPizza.values());
        configurarTabela();
        carregarTabela();
        listenerSelecao();
    }

    private void configurarTabela(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSabor.setCellValueFactory(new PropertyValueFactory<>("sabor"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
    }

    private void carregarTabela(){
        lista = FXCollections.observableArrayList(service.listar());
        tbPizza.setItems(lista);
        tbPizza.refresh();
    }

    private void carregarTabela(String parametro){
        lista = FXCollections.observableArrayList(service.listar(parametro));
        tbPizza.setItems(lista);
        tbPizza.refresh();
    }

    public void salvar(){
        try {
            Pizza p;
            if(pizzaSelecionada == null) {
                p = new Pizza();
            }else{
                p = pizzaSelecionada;
            }
            p.setSabor(tfSabor.getText());
            p.setTamanho(cbTamanho.getValue());
            p.setTipo(cbTipo.getValue());
            p.setBorda(cbBorda.getValue());
            p.setIngredientes(taIngredientes.getText());
            p.setPreco(Double.parseDouble(tfPreco.getText()));
            // mandar para a service salvar
            service.salvar(p);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Sucesso");
            alerta.setContentText("Pizza inserida com sucesso");
            alerta.setHeaderText("Sucesso");
            alerta.show();
            carregarTabela();
            limpar();
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText(e.getMessage());
            alerta.setHeaderText("Erro");
            alerta.setTitle("Erro");
            alerta.show();
        }
    }

    private void listenerSelecao() {
        tbPizza.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            pizzaSelecionada = newValue;
            if (pizzaSelecionada != null) {
                tfId.setText(pizzaSelecionada.getId().toString());
                tfSabor.setText(pizzaSelecionada.getSabor());
                taIngredientes.setText(pizzaSelecionada.getIngredientes());
                tfPreco.setText(pizzaSelecionada.getPreco() + "");
                cbTamanho.setValue(pizzaSelecionada.getTamanho());
                cbTipo.setValue(pizzaSelecionada.getTipo());
                cbBorda.setValue(pizzaSelecionada.getBorda());
            }
        });
    }

    public void excluir(){
        Alert alerta;
        if (pizzaSelecionada == null) {
            alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Selecione uma pizza para excluí-la");
            alerta.setHeaderText("Pizza não selecionada");
            alerta.show();
        } else {
            alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar exclusão");
            alerta.setHeaderText("Deseja excluir a pizza " + pizzaSelecionada.getSabor() + "?");
            alerta.setContentText("Escolha uma opção");
            alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> botaoClicado = alerta.showAndWait();
            if (botaoClicado.isPresent() && botaoClicado.get() == ButtonType.YES) {
                service.excluir(pizzaSelecionada);
                limpar();
                carregarTabela();
            }
        }
    }

    public void limpar(){
        tfId.clear();
        tfSabor.clear();
        taIngredientes.clear();
        tfPreco.clear();
        cbTipo.setValue(null);
        cbTamanho.setValue(null);
        pizzaSelecionada = null;
        cbBorda.setValue(null);
        tbPizza.getSelectionModel().clearSelection();
        tfSabor.requestFocus();
    }

    public void buscar(){
        String ingredientes = taIngredientes.getText().trim();
        carregarTabela(ingredientes);
        limpar();
    }
}
