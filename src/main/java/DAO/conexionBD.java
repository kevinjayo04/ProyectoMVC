/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author isaac
 */
public class conexionBD {   
     String url = "jdbc:mysql://localhost/registros";
     String user = "kevin";
     String clave = "132134Kj@";
     Connection conexion;
    

    //Metodo para la conexion a la base de datos.
    public Connection conexion() {
       
        try {
            Connection conexion = DriverManager.getConnection(url, user, clave);
            System.out.println("Conexión exitosa.");
            return conexion;
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
            return null;
        }
        
    }

    //Metodo para cerrar la conexion a la base de datos.
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }

    }

}
