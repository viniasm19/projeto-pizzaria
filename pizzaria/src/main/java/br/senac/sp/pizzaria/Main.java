package br.senac.sp.pizzaria;

import br.senac.sp.pizzaria.factory.ConnectionFactory;
import br.senac.sp.pizzaria.repository.JpaUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader
                (Main.class.getResource("/view/inicial-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pizzaria");
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}