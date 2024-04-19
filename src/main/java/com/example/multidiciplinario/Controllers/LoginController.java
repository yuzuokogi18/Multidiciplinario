package com.example.multidiciplinario.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.multidiciplinario.App;
import com.example.multidiciplinario.Models.Login;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button iniciarsesionButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button salirButton;

    @FXML
    private TextField usernameField;

    @FXML
    void onMouseClickiniciarsesionButton(MouseEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Por favor, ingrese su nombre de usuario y contraseña.");
            return;
        }

        Login login = new Login(username, password);

        if (login.validarIniciarSesion()) {
            System.out.println("Inicio de sesión exitoso para el usuario: " + username);
            try {
                Stage stage = (Stage) iniciarsesionButton.getScene().getWindow();
                App.changeScene("Menu.fxml", 1280, 800, stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Inicio de sesión fallido. Por favor, verifica tu nombre de usuario y contraseña.");
            mostrarAlerta("Inicio de sesión fallido. Por favor, verifica tu nombre de usuario y contraseña.");
        }

    }

    @FXML
    void onMouseClicksalirButton(MouseEvent event) {
        Stage stage = (Stage) salirButton.getScene().getWindow();
        stage.close();
    }

    protected void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuario");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    void onMouseEntered(MouseEvent event) {
        iniciarsesionButton.setStyle("-fx-background-color: darkblue;");
        iniciarsesionButton.setEffect(new DropShadow());
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        iniciarsesionButton.setStyle("-fx-background-color: blue;");
        iniciarsesionButton.setEffect(null);
    }
    @FXML
    void onMouseEnteredSalir(MouseEvent event) {
        salirButton.setStyle("-fx-background-color: darkblue;");
        salirButton.setEffect(new DropShadow());
    }

    @FXML
    void onMouseExitedSalir(MouseEvent event) {
        salirButton.setStyle("-fx-background-color: blue;");
        salirButton.setEffect(null);
    }

    @FXML
    void initialize() {

    }
}
