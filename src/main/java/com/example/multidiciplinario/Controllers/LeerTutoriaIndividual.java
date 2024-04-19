package com.example.multidiciplinario.Controllers;

import com.example.multidiciplinario.Models.Tutoria;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.format.DateTimeFormatter;

public class LeerTutoriaIndividual {

    @FXML
    private TableView<Tutoria> tutoriasTableView;

    @FXML
    private TableColumn<Tutoria, String> alumnoColumn;

    @FXML
    private TableColumn<Tutoria, String> descripcionColumn;

    @FXML
    private TableColumn<Tutoria, String> fechaHoraColumn;

    @FXML
    private TableColumn<Tutoria, String> tiempoColumn;
    private TutoriaIndividual tutoriaIndividualController;
    public void setTutoriaIndividualController(TutoriaIndividual tutoriaIndividualController) {
        this.tutoriaIndividualController = tutoriaIndividualController;
    }
    public void initialize() {
        alumnoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAlumno().getNombre() + " (" + data.getValue().getAlumno().getGrupo() + ")"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        fechaHoraColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaHoraRegistro().format(formatter)));
        tiempoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDuracionFormateada()));
        if (tutoriaIndividualController != null) {
            tutoriasTableView.setItems(tutoriaIndividualController.getTutorias());
        }
    }
    public void init() {
        initialize();
    }
}
