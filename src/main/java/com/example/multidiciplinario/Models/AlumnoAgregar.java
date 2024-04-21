package com.example.multidiciplinario.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlumnoAgregar {
    private String nombre;
    private String matricula;
    private String grupo;
    private String genero;
    private List<Tutoria> tutorias;

    public AlumnoAgregar(String nombre, String matricula, String grupo, String genero) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.grupo = grupo;
        this.genero = genero;
        this.tutorias = new ArrayList<>();
    }


    @Override
    public String toString() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getGenero() {
        return genero;
    }

    public List<Tutoria> getTutorias() {
        return tutorias;
    }

    public void setTutorias(List<Tutoria> tutorias) {
        this.tutorias = tutorias;
    }

    public void agregarTutoria(Tutoria tutoria) {
        this.tutorias.add(tutoria);
    }

    public AlumnoAgregar(String nombre, String matricula, int cantidadTutorias) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.tutorias = new ArrayList<>();
        for (int i = 0; i < cantidadTutorias; i++) {
            this.tutorias.add(new Tutoria(this, "", LocalDateTime.now(), 0));
        }
    }
}
