package com.example.multidiciplinario.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.example.multidiciplinario.Models.AlumnoAgregar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BuscarAlumno {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private ComboBox<String> alumnoComboBox;

    @FXML
    private Button buscaralumnoButton;

    @FXML
    private ComboBox<String> crearTutoriaComboBox;

    @FXML
    private ComboBox<?> eliminartutoriaComboBox;

    @FXML
    private ComboBox<String> generoComboBox;

    @FXML
    private TextField grupoTextField;

    @FXML
    private ComboBox<?> leertutoriaComboBox;

    @FXML
    private TextField matriculaAlumnoTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    void onMouseClickbuscaralumnoButton(ActionEvent event) {
        String nombre = nombreTextField.getText().trim();
        String matricula = matriculaAlumnoTextField.getText().trim();
        String genero = generoComboBox.getValue();
        String grupo = grupoTextField.getText().trim();

        if (!matricula.matches("\\d*")) {
            mostrarAlerta("Error", "La matrícula debe contener solo números.");
            return;
        }

        List<AlumnoAgregar> resultados = Alumno.getInstance().getListaAlumnos().stream()
                .filter(alumno -> (nombre.isEmpty() || alumno.getNombre().equalsIgnoreCase(nombre))
                        && (matricula.isEmpty() || alumno.getMatricula().equalsIgnoreCase(matricula))
                        && (genero == null || alumno.getGenero().equalsIgnoreCase(genero))
                        && (grupo.isEmpty() || alumno.getGrupo().equalsIgnoreCase(grupo)))
                .collect(Collectors.toList());

        if (resultados.isEmpty()) {
            mostrarAlerta("No se encontraron resultados", "No se encontraron alumnos con los criterios de búsqueda proporcionados.");
        } else {
            StringBuilder mensaje = new StringBuilder("Resultados de la búsqueda:\n\n");
            for (AlumnoAgregar alumno : resultados) {
                mensaje.append("Nombre: ").append(alumno.getNombre()).append("\n")
                        .append("Matrícula: ").append(alumno.getMatricula()).append("\n")
                        .append("Género: ").append(alumno.getGenero()).append("\n")
                        .append("Grupo: ").append(alumno.getGrupo()).append("\n\n");
            }
            mostrarAlerta("Resultados de la búsqueda", mensaje.toString());
        }
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void onMouseEntered(MouseEvent event) {
        buscaralumnoButton.setStyle("-fx-background-color: darkblue;");
        buscaralumnoButton.setEffect(new DropShadow());
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        buscaralumnoButton.setStyle("-fx-background-color: blue;");
        buscaralumnoButton.setEffect(null);
    }


    @FXML
    void initialize() {
        alumnoComboBox.getItems().addAll("Agregar Alumno", "Buscar Alumno", "Eliminar Alumno", "Actualizar Alumno");
        generoComboBox.getItems().addAll("Masculino", "Femenino", "Otro");
        alumnoComboBox.setOnAction(this::handleComboBoxSelection);
        crearTutoriaComboBox.getItems().addAll("Tutoría Individual", "Tutoría Grupal");
        crearTutoriaComboBox.setOnAction(event -> {
            String selectedOption = (String) crearTutoriaComboBox.getSelectionModel().getSelectedItem();
            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Tutoría Individual":
                        abrirVentanaTutoriaIndividual();
                        break;
                    case "Tutoría Grupal":
                        abrirVentanaTutoriaGrupal();
                        break;
                }
            }
        });

    }

    public void handleComboBoxSelection(ActionEvent event) {
        String selectedOption = alumnoComboBox.getValue();
        switch (selectedOption) {
            case "Agregar Alumno":
                abrirVentanaAgregarAlumno();
                break;
            case "Buscar Alumno":
                abrirVentanaBuscarAlumno();
                break;
            case "Eliminar Alumno":
                abrirVentanaEliminarAlumno();
                break;
            case "Actualizar Alumno":
                abrirVentanaActualizarAlumno();
                break;
            default:
                break;
        }
    }

    private void abrirVentanaAgregarAlumno() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/AgregarAlumno.fxml"));
            fxmlLoader.setControllerFactory(t -> new AgregarAlumno());
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaBuscarAlumno() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/BuscarAlumno.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaEliminarAlumno() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/EliminarAlumno.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaActualizarAlumno() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/ActualizarAlumno.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void abrirVentanaTutoriaIndividual(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/TutoriaIndividual.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void abrirVentanaTutoriaGrupal(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/TutoriaGrupal.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
