package com.example.multidiciplinario.Controllers;

import com.example.multidiciplinario.Models.AlumnoAgregar;
import com.example.multidiciplinario.Models.Tutoria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class BuscarTutoria {
    @FXML
    private TextField matriculaTextField;

    @FXML
    private Button buscarButton;

    @FXML
    private TableView<Tutoria> tutoriasTableView;

    @FXML
    private TableColumn<Tutoria, String> descripcionColumn;

    @FXML
    private TableColumn<Tutoria, String> fechaHoraRegistroColumn;

    @FXML
    private TableColumn<Tutoria, Integer> tiempoColumn;

    private ObservableList<Tutoria> tutoriasEncontradas = FXCollections.observableArrayList();

    private TutoriaIndividual tutoriaIndividualController;

    public void setTutoriaIndividualController(TutoriaIndividual tutoriaIndividualController) {
        this.tutoriaIndividualController = tutoriaIndividualController;
    }

    @FXML
    void buscarTutoriasPorMatricula(ActionEvent event) {
        String matricula = matriculaTextField.getText().trim();
        if (!matricula.isEmpty()) {
            List<Tutoria> tutorias = tutoriaIndividualController.buscarTutoriasPorMatricula(matricula);
            if (tutorias.isEmpty()) {
                mostrarAlerta("No se encontraron tutorías", "No hay tutorías registradas para el alumno con esa matrícula.");
            } else {
                tutoriasEncontradas.setAll(tutorias);
            }
        } else {
            mostrarAlerta("Error", "Ingrese la matrícula del alumno.");
        }
    }

    @FXML
    void initialize() {
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        fechaHoraRegistroColumn.setCellValueFactory(new PropertyValueFactory<>("fechaHoraRegistro"));
        tiempoColumn.setCellValueFactory(new PropertyValueFactory<>("tiempo"));

        tutoriasTableView.setItems(tutoriasEncontradas);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
