package com.io.sqlite.entidades;

import android.content.Intent;

import java.io.Serializable;

public class usuario implements Serializable{  //ponemos implements Serializable para poder pasar datos entre actividades para poder pasar datos como parámetro

    private Integer id;
    private String nombre;
    private String telefono;

    public usuario(Integer id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public usuario(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}