/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.vehiculoDAO;
import DTO.historial;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.vehiculo;

/**
 *
 * @author isaac
 */
public class vehiculoService {

    private vehiculoDAO vehiculoDao;

    public vehiculoService() {
        vehiculoDao = new vehiculoDAO();

    }

    public List<vehiculo> obtenerVehiculo() {
        return vehiculoDao.mostrar();

    }

    public List<vehiculo> obtVehiSinAso(boolean noAsociado) {
        return vehiculoDao.obtVehSinAso(noAsociado);

    }

    public List<historial> obtHisDVehi(String matricula) {
        return vehiculoDao.obtHistVeh(matricula);

    }

    public boolean eliminarVehiculoPorMatri(String matricula) {
        vehiculo vehiculoEliminar = new vehiculo();
        vehiculoEliminar.setMatricula(matricula);
        try {
            vehiculoDao.borrar(vehiculoEliminar);
            return true;
        } catch (Exception e) {
            System.out.println("Error en el servicio Eliminar:" + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarVehiculoMatricula(vehiculo objeto){
    return vehiculoDao.actualizarVehiculoPorMatricula(objeto);
    
    }

}
