package com.example.multidiciplinario.Controllers;

import com.example.multidiciplinario.Models.Tutoria;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.time.format.DateTimeFormatter;
import java.util.List;

public class LeerTutoriaGrupal {

    @FXML
    private TableView<Tutoria> tutoriasTableView;

    @FXML
    private TableColumn<Tutoria, String> grupoColumn;

    @FXML
    private TableColumn<Tutoria, String> temaColumn;

    @FXML
    private TableColumn<Tutoria, String> fechaHoraColumn;

    @FXML
    private TableColumn<Tutoria, String> duracionColumn;
    private TutoriaGrupal tutoriaGrupalController;
    public void setTutoriaGrupalController(TutoriaGrupal tutoriaGrupalController) {
        this.tutoriaGrupalController = tutoriaGrupalController;
    }
    public void initialize() {
        grupoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombreGrupo()));
        temaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTema()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        fechaHoraColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaHoraRegistro().format(formatter)));
        duracionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDuracionFormateada()));

        if (tutoriaGrupalController != null) {
            tutoriasTableView.setItems(tutoriaGrupalController.getTutoriasGrupales());
        }
    }

    public void setTutoriasGrupales(List<Tutoria> tutoriasGrupales) {
        tutoriasTableView.setItems(FXCollections.observableArrayList(tutoriasGrupales));
    }
}

