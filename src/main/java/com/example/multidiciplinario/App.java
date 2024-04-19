package com.example.multidiciplinario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class  App extends Application {
    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stg.setTitle("Iniciar Sesion!");
        stg.setScene(scene);
        stg.show();
    }

    public static void changeScene(String fxml, double width, double height, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, width, height);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
