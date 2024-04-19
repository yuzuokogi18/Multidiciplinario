package com.example.multidiciplinario.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.multidiciplinario.Models.AlumnoAgregar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EliminarAlumno {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ComboBox<String> alumnoComboBox;
    @FXML
    private ComboBox<String> generoComboBox;
    @FXML
    private ComboBox<String> crearTutoriaComboBox;

    @FXML
    private Button eliminaralumnoButton;

    @FXML
    private ComboBox<?> eliminartutoriaComboBox;

    @FXML
    private ComboBox<?> leertutoriaComboBox;

    @FXML
    private TextField matriculaAlumnoTextField;

    @FXML
    void onMouseClickeliminaralumnoButton(MouseEvent event) {
        String matricula = matriculaAlumnoTextField.getText().trim();

        if (!matricula.matches("\\d*")) {
            mostrarAlerta("Error", "La matrícula debe contener solo numeros.");
            return;
        }

        AlumnoAgregar alumnoAEliminar = buscarAlumnoPorMatricula(matricula);

        if (alumnoAEliminar != null) {
            mostrarAlertaConfirmacion(alumnoAEliminar);
        } else {
            mostrarAlerta("Error", "No se encontró ningún alumno con la matrícula proporcionada.");
        }
    }

    private AlumnoAgregar buscarAlumnoPorMatricula(String matricula) {
        return Alumno.getInstance().getListaAlumnos().stream()
                .filter(alumno -> alumno.getMatricula().equalsIgnoreCase(matricula))
                .findFirst()
                .orElse(null);
    }

    private void mostrarAlertaConfirmacion(AlumnoAgregar alumno) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de eliminación");
        alert.setHeaderText("¿Está seguro de eliminar este alumno?");
        alert.setContentText("Nombre: " + alumno.getNombre() + "\n" + "Matrícula: " + alumno.getMatricula() + "\n" + "Género: " + alumno.getGenero() + "\n" + "Grupo: " + alumno.getGrupo());

        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        yesButton.setText("Sí");
        Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        noButton.setText("No");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            eliminarAlumno(alumno);
        }
    }

    private void eliminarAlumno(AlumnoAgregar alumno) {
        Alumno.getInstance().getListaAlumnos().remove(alumno);
        mostrarAlerta("Éxito", "El alumno ha sido eliminado correctamente.");
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
        eliminaralumnoButton.setStyle("-fx-background-color: darkblue;");
        eliminaralumnoButton.setEffect(new DropShadow());
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        eliminaralumnoButton.setStyle("-fx-background-color: blue;");
        eliminaralumnoButton.setEffect(null);
    }


    @FXML
    void initialize() {
        alumnoComboBox.getItems().addAll("Agregar Alumno", "Buscar Alumno", "Eliminar Alumno", "Actualizar Alumno");
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
