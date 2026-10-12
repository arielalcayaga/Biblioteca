package com.biblioteca.model;

public class Prestamo {

    private int id;
    private int id_estudiante;
    private int id_libro;
    private String fecha_prestamo;
    private String fecha_devolucion;
    private boolean devuelto;

    public Prestamo(int id, int id_estudiante, int id_libro, String fecha_prestamo, String fecha_devolucion, boolean devuelto) {
        this.id = id;
        this.id_estudiante = id_estudiante;
        this.id_libro = id_libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.devuelto = devuelto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", id_estudiante=" + id_estudiante +
                ", id_libro=" + id_libro +
                ", fecha_prestamo='" + fecha_prestamo + '\'' +
                ", fecha_devolucion='" + fecha_devolucion + '\'' +
                ", devuelto=" + devuelto +
                '}';
    }
}
