package com.example.multidiciplinario.Controllers;

import com.example.multidiciplinario.Models.AlumnoAgregar;
import com.example.multidiciplinario.Models.Tutoria;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TutoriasAtendidas {

    @FXML
    private TableView<AlumnoAgregar> tutoriasTableView;

    @FXML
    private TableColumn<AlumnoAgregar, String> alumnoColumn;

    @FXML
    private TableColumn<AlumnoAgregar, String> matriculaColumn;

    @FXML
    private TableColumn<AlumnoAgregar, String> fechaCreacionColumn;

    private ObservableList<AlumnoAgregar> alumnosConTutoriasObservableList;

    @FXML
    public void initialize() {
        // Inicializar la lista de alumnos con tutorías
        alumnosConTutoriasObservableList = FXCollections.observableArrayList();

        // Configurar las columnas de la tabla
        alumnoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        matriculaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMatricula()));
        fechaCreacionColumn.setCellValueFactory(data -> {
            List<Tutoria> tutorias = data.getValue().getTutorias();
            if (tutorias.isEmpty()) {
                return new SimpleStringProperty("Sin tutorías");
            } else {
                StringBuilder fechas = new StringBuilder();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                for (Tutoria tutoria : tutorias) {
                    LocalDateTime fechaCreacion = tutoria.getFechaHoraRegistro();
                    String fechaFormateada = fechaCreacion.format(formatter);
                    fechas.append(fechaFormateada).append("\n");
                }
                return new SimpleStringProperty(fechas.toString());
            }
        });

        // Establecer los datos de la tabla
        tutoriasTableView.setItems(alumnosConTutoriasObservableList);
    }

    public void setAlumnosConTutorias(List<AlumnoAgregar> listaAlumnos) {
        // Limpiar la lista actual y agregar los nuevos alumnos con tutorías
        alumnosConTutoriasObservableList.clear();
        alumnosConTutoriasObservableList.addAll(listaAlumnos);
    }
}
