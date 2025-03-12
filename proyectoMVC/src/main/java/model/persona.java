/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author isaac
 */
public class persona {

    private int id;
    private String dni;
    protected String nombre;
    public enum genero {
        MASCULINO, FEMENINO
    };
    protected genero genero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public genero getGenero() {
        return genero;
    }

    public void setGenero(genero genero) {
        this.genero = genero;
    }

    public persona() {
    }

    public persona(int id, String dni, String nombre, genero genero) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.genero = genero;
    }

    public persona(String dni, String nombre, String generoStr) {
        this.dni = dni;
        this.nombre = nombre;
        this.genero = genero.valueOf(generoStr.toUpperCase());
    }
    
    

}
