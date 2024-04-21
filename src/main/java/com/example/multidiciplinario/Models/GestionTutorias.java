package com.example.multidiciplinario.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionTutorias {
    private static final ObservableList<Tutoria> tutorias = FXCollections.observableArrayList();
    public static ObservableList<Tutoria> getTutorias() {
        return tutorias;
    }
    public static void agregarTutoria(Tutoria tutoria) {
        tutorias.add(tutoria);
    }

    public static void eliminarTutoria(Tutoria tutoria) {
        tutorias.remove(tutoria);
    }
}
