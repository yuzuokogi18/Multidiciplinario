package com.example.multidiciplinario.Controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class AgendarCita {

    @FXML
    private Button AgendarButton;
    @FXML
    private TextField matriculaGrupoTextField;
    @FXML
    private DatePicker fechaCitaDatePicker;
    @FXML
    private TableView<Cita> tutoriasTableView;
    @FXML
    private TableColumn<Cita, String> matriculaGrupoColumn;
    @FXML
    private TableColumn<Cita, LocalDate> fechaCitaColumn;

    private ObservableList<Cita> citasList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        matriculaGrupoColumn.setCellValueFactory(cellData -> cellData.getValue().matriculaGrupoProperty());
        fechaCitaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaCitaProperty());

        tutoriasTableView.setItems(citasList);
    }

    @FXML
    void agendarCita(ActionEvent event) {
        String matriculaOGrupo = matriculaGrupoTextField.getText().trim();
        LocalDate fechaCita = fechaCitaDatePicker.getValue();

        if (matriculaOGrupo.isEmpty() || fechaCita == null) {
            mostrarAlerta("Error", "Por favor, ingrese la matrícula del alumno o nombre del grupo y seleccione una fecha.");
            return;
        }

        boolean citaProgramada = agregarCita(matriculaOGrupo, fechaCita);
        if (citaProgramada) {
            mostrarAlerta("Cita Agendada", "Se ha agendado la cita correctamente.");
        } else {
            mostrarAlerta("Error", "No se pudo agendar la cita. Por favor, inténtelo de nuevo.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean agregarCita(String matriculaOGrupo, LocalDate fechaCita) {
        Cita nuevaCita = new Cita(matriculaOGrupo, fechaCita);
        citasList.add(nuevaCita);
        return true;
    }

    public static class Cita {
        private final String matriculaGrupo;
        private final LocalDate fechaCita;

        public Cita(String matriculaGrupo, LocalDate fechaCita) {
            this.matriculaGrupo = matriculaGrupo;
            this.fechaCita = fechaCita;
        }

        public String getMatriculaGrupo() {
            return matriculaGrupo;
        }

        public LocalDate getFechaCita() {
            return fechaCita;
        }

        public StringProperty matriculaGrupoProperty() {
            return new SimpleStringProperty(matriculaGrupo);
        }

        public ObjectProperty<LocalDate> fechaCitaProperty() {
            return new SimpleObjectProperty<>(fechaCita);
        }
    }
}
