package com.example.multidiciplinario.Models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Tutoria  {
    private AlumnoAgregar alumno;
    private String descripcion;
    private LocalDateTime fechaHoraRegistro;
    private int tiempo;
    private List<AlumnoAgregar> alumnos;
    private Duration duracion;
    private String tema;
    private String nombreGrupo;

    public Tutoria(List<AlumnoAgregar> alumnos, String descripcion, LocalDateTime fechaHoraRegistro, int tiempo, String tema, String nombreGrupo) {
        this.alumnos = alumnos;
        this.descripcion = descripcion;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.tiempo = tiempo;
        this.tema = tema;
        this.nombreGrupo = nombreGrupo; // Asignar el nombre del grupo
        this.duracion = Duration.ofMinutes(tiempo);
    }


    public Tutoria(AlumnoAgregar alumno, String descripcion, LocalDateTime fechaHoraRegistro, int tiempo) {
        this.alumno = alumno;
        this.descripcion = descripcion;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public AlumnoAgregar getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoAgregar alumno) {
        this.alumno = alumno;
    }
    public String getNombreGrupo() {
        return this.nombreGrupo;
    }
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public LocalDateTime getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }


    public String getDuracionFormateada() {
        if (duracion != null) {
            long horas = duracion.toHours();
            long minutos = duracion.toMinutesPart();
            return String.format("%d:%02d", horas, minutos);
        } else {
            return "Duración no disponible";
        }

    }

    public String getGrupo() {
        // Si la lista de alumnos no está vacía
        if (!alumnos.isEmpty()) {
            // Retornar el grupo del primer alumno
            return alumnos.get(0).getGrupo();
        } else {
            // Si la lista de alumnos está vacía, retorna un mensaje indicando que no hay grupo
            return "Sin grupo asignado";
        }
    }
}
