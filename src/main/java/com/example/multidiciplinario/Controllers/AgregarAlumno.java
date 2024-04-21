package com.example.multidiciplinario.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class AgregarAlumno {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String > actualizartutoriasComboBox;

    @FXML
    private Button agregaralumnoButton;

    @FXML
    private ComboBox<String> alumnoComboBox;

    @FXML
    private ComboBox<String> crearTutoriaComboBox;

    @FXML
    private ComboBox<?> eliminartutoriaComboBox;

    @FXML
    private ComboBox<String> generoComboBox;

    @FXML
    private ComboBox<?> leertutoriaComboBox;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField matriculaTextField;

    @FXML
    private TextField grupoTextField;

    @FXML
    void onMouseClickagregaralumnoButton(MouseEvent event) {
        agregarAlumno();
    }
    private void agregarAlumno() {
        String nombre = nombreTextField.getText().trim();
        String matricula = matriculaTextField.getText().trim();
        String grupo = grupoTextField.getText().trim();
        String genero = generoComboBox.getValue();

        if (nombre.isEmpty() || matricula.isEmpty() || grupo.isEmpty() || genero.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        try {
            int matriculaNum = Integer.parseInt(matricula);
            AlumnoAgregar nuevoAlumno = new AlumnoAgregar(nombre, matricula, grupo, genero);
            Alumno.getInstance().getListaAlumnos().add(nuevoAlumno);
            limpiarCampos();
            System.out.println("Tamaño de la lista de alumnos: " + Alumno.getInstance().getListaAlumnos().size());
            System.out.println("Información del alumno agregado:");
            System.out.println("Nombre: " + nuevoAlumno.getNombre());
            System.out.println("Matrícula: " + nuevoAlumno.getMatricula());
            System.out.println("Grupo: " + nuevoAlumno.getGrupo());
            System.out.println("Género: " + nuevoAlumno.getGenero());
            mostrarAlerta("Éxito", "Alumno agregado correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La matrícula debe ser un número entero válido.");
        }
    }

    private void limpiarCampos() {
        nombreTextField.clear();
        matriculaTextField.clear();
        grupoTextField.clear();
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
        agregaralumnoButton.setStyle("-fx-background-color: darkblue;");
        agregaralumnoButton.setEffect(new DropShadow());
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        agregaralumnoButton.setStyle("-fx-background-color: blue;");
        agregaralumnoButton.setEffect(null);
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
            TutoriaIndividual tutoriaIndividualController = fxmlLoader.getController();
            tutoriaIndividualController.actualizarListaAlumnos(Alumno.getInstance().getListaAlumnos());
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
