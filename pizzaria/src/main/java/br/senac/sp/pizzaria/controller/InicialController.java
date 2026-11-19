package br.senac.sp.pizzaria.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InicialController {
    public void sair(){
        System.exit(0);
    }

    public void cadCliente(){
        try{
            FXMLLoader loader = new
                    FXMLLoader(getClass().getResource("/view/cliente-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Cadastro de Clientes");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void cadPizza(){
        try{
            FXMLLoader loader = new
                    FXMLLoader(getClass().getResource("/view/pizza-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Cadastro de Pizzas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void pedido(){
        try{
            FXMLLoader loader = new
                    FXMLLoader(getClass().getResource("/view/pedido-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Emissão de Pedidos");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void verPedidos(){

    }
}
