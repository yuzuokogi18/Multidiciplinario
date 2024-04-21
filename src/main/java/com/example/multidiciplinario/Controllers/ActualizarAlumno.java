package com.example.multidiciplinario.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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

public class ActualizarAlumno {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button actualizaralumnoButton;


    @FXML
    private ComboBox<String> alumnoComboBox;

    @FXML
    private ComboBox<String> crearTutoriaComboBox;

    @FXML
    private ComboBox<?> eliminartutoriaComboBox;

    @FXML
    private TextField grupoTextField;

    @FXML
    private ComboBox<?> leertutoriaComboBox;

    @FXML
    private TextField matriculaTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    void onMouseClickactualizaralumnoButton(ActionEvent event) {
        String matricula = matriculaTextField.getText().trim();
        String nuevoNombre = nombreTextField.getText().trim();
        String nuevoGrupo = grupoTextField.getText().trim();

        AlumnoAgregar alumno = buscarAlumnoPorMatricula(matricula);
        if (alumno != null) {
            mostrarAlertaConfirmacion(alumno, nuevoNombre, nuevoGrupo);
        } else {
            mostrarAlerta("Error", "No se encontró ningún alumno con la matrícula proporcionada.");
        }
    }

    private AlumnoAgregar buscarAlumnoPorMatricula(String matricula) {
        List<AlumnoAgregar> listaAlumnos = Alumno.getInstance().getListaAlumnos();
        for (AlumnoAgregar alumno : listaAlumnos) {
            if (alumno.getMatricula().equalsIgnoreCase(matricula)) {
                return alumno;
            }
        }
        return null;
    }

    private void mostrarAlertaConfirmacion(AlumnoAgregar alumno, String nuevoNombre, String nuevoGrupo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de actualización");
        alert.setHeaderText("¿Está seguro de actualizar este alumno?");
        alert.setContentText("Nombre actual: " + alumno.getNombre() + "\n" + "Nuevo nombre: " + nuevoNombre + "\n" + "Grupo actual: " + alumno.getGrupo() + "\n" + "Nuevo grupo: " + nuevoGrupo + "\n");
        ButtonType yesButton = new ButtonType("Sí");
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            actualizarAlumno(alumno, nuevoNombre, nuevoGrupo);
        }
    }
    private void actualizarAlumno(AlumnoAgregar alumno, String nuevoNombre, String nuevoGrupo) {
        if (!nuevoNombre.isEmpty()) {
            alumno.setNombre(nuevoNombre);
        }
        if (!nuevoGrupo.isEmpty()) {
            alumno.setGrupo(nuevoGrupo);
        }
        List<AlumnoAgregar> listaAlumnos = Alumno.getInstance().getListaAlumnos();
        for (int i = 0; i < listaAlumnos.size(); i++) {
            if (listaAlumnos.get(i).getMatricula().equals(alumno.getMatricula())) {
                listaAlumnos.set(i, alumno);
                break;
            }
        }

        mostrarAlerta("Éxito", "El alumno ha sido actualizado correctamente.");
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
        actualizaralumnoButton.setStyle("-fx-background-color: darkblue;");
        actualizaralumnoButton.setEffect(new DropShadow());
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        actualizaralumnoButton.setStyle("-fx-background-color: blue;");
        actualizaralumnoButton.setEffect(null);
    }

    @FXML
    void initialize() {
        alumnoComboBox.getItems().addAll("Agregar Alumno", "Buscar Alumno", "Eliminar Alumno", "Actualizar Alumno");
        crearTutoriaComboBox.getItems().addAll("Tutoría Individual", "Tutoría Grupal");
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
        String tutoriaOption = crearTutoriaComboBox.getValue();
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

}
