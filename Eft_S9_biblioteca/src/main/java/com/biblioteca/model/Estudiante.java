package com.biblioteca.model;

public class Estudiante {

    private int id;
    private String nombre;
    private String run;
    private String curso;
    private String correo;

    public Estudiante(int id, String nombre, String run, String curso, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.run = run;
        this.curso = curso;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", run='" + run + '\'' +
                ", curso='" + curso + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
