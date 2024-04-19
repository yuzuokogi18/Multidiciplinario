package com.example.multidiciplinario.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionTutorias {
    private static final ObservableList<Tutoria> tutorias = FXCollections.observableArrayList();

    // Método para obtener la lista de tutorías
    public static ObservableList<Tutoria> getTutorias() {
        return tutorias;
    }

    // Método para agregar una tutoría a la lista
    public static void agregarTutoria(Tutoria tutoria) {
        tutorias.add(tutoria);
    }

    // Método para eliminar una tutoría de la lista
    public static void eliminarTutoria(Tutoria tutoria) {
        tutorias.remove(tutoria);
    }
}
