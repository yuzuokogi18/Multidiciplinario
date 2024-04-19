package com.example.multidiciplinario.Controllers;

import com.example.multidiciplinario.Models.Tutoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView; // Importa TableView
import javafx.stage.Stage;

import java.io.IOException;

public class EliminarTutoriaIndividual {

    @FXML
    private ComboBox<String> alumnoComboBox;
    @FXML
    public ComboBox<String> leertutoriaComboBox;
    public ComboBox eliminartutoriaComboBox;
    @FXML
    private ComboBox<String> crearTutoriaComboBox;
    @FXML
    private Button eliminaralumnoButton;
    @FXML
    private TableView<Tutoria> leerTutoriasIndividuales; // Declarar TableView

    @FXML
    private TextField matriculaAlumnoTextField;

    @FXML
    void onMouseClickeliminaralumnoButton(MouseEvent event) {

        String matricula = matriculaAlumnoTextField.getText().trim();

        // Validar que la matrícula no esté vacía
        if (matricula.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingrese la matrícula del alumno.");
            return;
        }

        // Aquí debes obtener la información de la tutoría individual seleccionada en la tabla
        Tutoria tutoriaSeleccionada = obtenerTutoriaSeleccionada();

        if (tutoriaSeleccionada == null) {
            mostrarAlerta("Error", "Por favor, seleccione una tutoría individual para eliminar.");
            return;
        }

        // Eliminar la tutoría individual
        TutoriaIndividual tutoriaIndividualController = new TutoriaIndividual();
        tutoriaIndividualController.eliminarTutoria(tutoriaSeleccionada);

        mostrarAlerta("Tutoría Eliminada", "Se ha eliminado la tutoría individual correctamente.");
    }

    private Tutoria obtenerTutoriaSeleccionada() {
        // Verificar si hay una fila seleccionada en la tabla
        int indiceFilaSeleccionada = leerTutoriasIndividuales.getSelectionModel().getSelectedIndex();

        if (indiceFilaSeleccionada != -1) {
            // Obtener la tutoría individual asociada a la fila seleccionada
            return leerTutoriasIndividuales.getItems().get(indiceFilaSeleccionada);
        } else {
            // Si no hay ninguna fila seleccionada, devolver null
            return null;
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

    }

    @FXML
    void onMouseExited(MouseEvent event) {
    }

    @FXML
    void initialize() {
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

        eliminartutoriaComboBox.getItems().addAll("Eliminar Tutoría Individual", "Eliminar Tutoría Grupal");
        eliminartutoriaComboBox.setOnAction(event -> {
            String selectedOption = (String) eliminartutoriaComboBox.getSelectionModel().getSelectedItem();
            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Eliminar Tutoría Individual":
                        abrirVentanaEliminarTutoriaIndividual();

                        break;
                    case "Eliminar Tutoría Grupal":

                        break;
                }
            }
        });
        leertutoriaComboBox.getItems().addAll("Leer Tutoría Individual", "Leer Tutoría Grupal", "Tutorias Atendidas", "Tutorias No Atendidas");
        leertutoriaComboBox.setOnAction(event -> {
            String selectedOption = leertutoriaComboBox.getValue();
            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Leer Tutoría Individual":
                        abrirVentanaLeerTutorias();
                        break;
                    case "Leer Tutoría Grupal":
                        abrirVentanaLeerTutoriaGrupal();
                        break;
                    case "Tutorias Atendidas":
                        // Agrega aquí la lógica para mostrar las tutorías atendidas
                        break;
                    case "Tutorias No Atendidas":
                        // Agrega aquí la lógica para mostrar las tutorías no atendidas
                        break;
                }
            }
        });

    }

    public void handleComboBoxSelection(ActionEvent event) {
        String selectedOption = String.valueOf(alumnoComboBox.getValue());
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

    private void abrirVentanaTutoriaIndividual() {
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

    private void abrirVentanaTutoriaGrupal() {
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

    private void abrirVentanaLeerTutorias() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/LeerTutoriaIndividual.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaLeerTutoriaGrupal() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/LeerTutoriaGrupal.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaEliminarTutoriaIndividual() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/EliminarTutoriaIndividual.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}