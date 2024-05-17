package com.backend.parcial.model;

public class Odontologo {
    private int id;
    private String numeroMatricula;
    private String nombre;
    private String apellido;

    public Odontologo(String numeroMatricula, String nombre, String apellido) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Odontologo) {
            Odontologo o =  (Odontologo) obj;
            return this.nombre.equals(o.getNombre()) && this.apellido.equals(o.getApellido());
        } else {
            return false;
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}