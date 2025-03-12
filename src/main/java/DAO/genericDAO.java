/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author isaac
 */
public abstract class genericDAO<T> {
    
    //Plantilla de metodos para el DAO 
    public abstract void insertar(T objeto);

    public abstract void borrar(T objeto);

    public abstract List<T> mostrar();

    public abstract void actualizar(T objeto);

    public abstract T buscarPorId(int id);
}
