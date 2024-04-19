package com.example.multidiciplinario.Controllers;

import com.example.multidiciplinario.Models.AlumnoAgregar;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private static Alumno instance;
    private List<AlumnoAgregar> listaAlumnos;

    private Alumno() {
        listaAlumnos = new ArrayList<>();
    }

    public static Alumno getInstance() {
        if (instance == null) {
            instance = new Alumno();
        }
        return instance;
    }

    public List<AlumnoAgregar> getListaAlumnos() {
        return listaAlumnos;
    }

    public AlumnoAgregar buscarAlumnoPorMatricula(String matricula) {
        for (AlumnoAgregar alumno : listaAlumnos) {
            if (alumno.getMatricula().equals(matricula)) {
                return alumno;
            }
        }
        return null;
    }
}
