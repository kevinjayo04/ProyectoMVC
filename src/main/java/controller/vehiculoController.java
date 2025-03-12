/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.conexionBD;
import DAO.personaDAO;
import DAO.vehiculoDAO;
import DTO.historial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.vehiculo;
import service.vehiculoService;

/**
 *
 * @author isaac
 */
public class vehiculoController {

    vehiculoDAO vehiculoDao;
    personaDAO personaDao;
    vehiculoService vehiculoSrv;
    conexionBD con;

    public vehiculoController() {
        vehiculoDao = new vehiculoDAO();
        personaDao = new personaDAO();
        vehiculoSrv = new vehiculoService();
        con = new conexionBD();

    }

    public void registroVehiculo(String matricula, int año, String marca, String modelo) {
        vehiculo veh = new vehiculo(matricula, año, marca, modelo);
        try {
            vehiculoDao.insertar(veh);
            System.out.println("vehiculo insertado con exito.");
        } catch (Exception e) {
            System.out.println("Error al registro de vehiculo: " + e.getMessage());
        }

    }

    public DefaultTableModel cargarVehiculoTb() {

        String[] columna = {"Matricula", "Año", "Marca", "Modelo"};
        DefaultTableModel model = new DefaultTableModel(columna, 0);

        try {
            List<vehiculo> listadoVehiculo = vehiculoSrv.obtenerVehiculo();

            for (vehiculo v : listadoVehiculo) {
                Object[] fila = {v.getMatricula(), v.getAño(), v.getMarca(), v.getModelo()};
                model.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar vehiculos: " + e.getMessage());
        }

        return model;
    }

    public DefaultTableModel obtVehiAso(boolean mostrarNoAsociados) {

        String[] columna = {"Matricula", "Año", "Marca", "Modelo"};
        DefaultTableModel model = new DefaultTableModel(columna, 0);
        try {
            List<vehiculo> vehiculos = vehiculoSrv.obtVehiSinAso(mostrarNoAsociados);

            for (vehiculo v : vehiculos) {
                model.addRow(new Object[]{v.getMatricula(), v.getAño(), v.getMarca(), v.getModelo()});
            }

        } catch (Exception e) {
            System.out.println("Error al obtener vehiculos según asociacion: " + e.getMessage());
        }
        return model;
    }

    public DefaultTableModel obtHistVehic(String matricula) {
        // Columnas para la tabla
        String[] columnas = {"DNI", "Nombre", "Fecha Inicio", "Fecha Fin"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        try {
            // Obtengo el historial del vehiculo
            List<historial> hist = vehiculoSrv.obtHisDVehi(matricula);

            // Agrego cada historial al modelo de la tabla
            for (historial h : hist) {
                // Agrego una fila con el DNI, nombre, fecha de inicio y fecha de fin
                model.addRow(new Object[]{
                    h.getDni(),
                    h.getNombre(),
                    h.getFecha_inicio(),
                    h.getFecha_fin()
                });
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el historial: " + e.getMessage());
        }

        return model;
    }

    public boolean asoVehiConPer(String dni, String matricula) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = con.conexion();
            conn.setAutoCommit(false); // Iniciamos la transaccion.

            // Obtengo los IDs de persona y vehiculo
            int personaId = personaDao.obtPersIdporDNI(dni);
            int vehiculoId = vehiculoDao.obtVehiIdPorMatri(matricula);

            // Inserto la nueva asociacion en el historial
            String sql = "INSERT INTO historial (id_persona, id_vehiculo, fecha_inicio) VALUES (?, ?, NOW())";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, personaId);
            pstmt.setInt(2, vehiculoId);

            int rowsHistorial = pstmt.executeUpdate();

            // Actualizo la columna persona_id en la tabla vehiculo
            String sqlVehiculo = "UPDATE vehiculo SET persona_id = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sqlVehiculo);
            pstmt.setInt(1, personaId);  // Asocio la persona al vehiculo
            pstmt.setInt(2, vehiculoId); // Uso el id del vehiculo
            int rowsVehiculo = pstmt.executeUpdate();

            // Si ambas actualizaciones fueron exitosas, confirmamos la transaccion
            if (rowsHistorial > 0 && rowsVehiculo > 0) {
                conn.commit(); // Confirmamos la transaccion
                return true;
            }

            // Si no se actualizaron las filas correctamente, hacemos rollback
            conn.rollback();
            return false;

        } catch (SQLException e) {
            System.out.println("Error al asociar: " + e.getMessage());
            if (conn != null) {
                conn.rollback(); // Hacemos rollback en caso de error
            }
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public boolean desasociarVehiculoDePersona(String dni, String matricula) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = con.conexion();
            conn.setAutoCommit(false); // Inicio una transaccion

            //Obtengo los IDs de persona y vehiculo
            int personaId = personaDao.obtPersIdporDNI(dni);
            int vehiculoId = vehiculoDao.obtVehiIdPorMatri(matricula);

            //Desasociar el vehiculo de la persona en historial
            String sql = "UPDATE vehiculo SET persona_id = NULL WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vehiculoId);
            int rows = stmt.executeUpdate();

            conn.commit(); // Confirmamos la transaccion
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error al desasociar");
            if (conn != null) {
                conn.rollback(); // Hacemos rollback en caso de error
            }
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }

        }
    }

    public boolean actualizarFechaFinHistorial(String dni, String matricula) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = con.conexion();
            conn.setAutoCommit(false); // Inicio una transaccion

            //Obtengo los IDs de persona y vehiculo a partir de los DNI y matricula
            int personaId = personaDao.obtPersIdporDNI(dni);  // Metodo para obtener el ID de la persona por su DNI
            int vehiculoId = vehiculoDao.obtVehiIdPorMatri(matricula);  // Metodo para obtener el ID del vehiculo por su matricula

            //Actualiza la fecha_fin del historial
            String sql = "UPDATE historial SET fecha_fin = NOW() WHERE id_persona = ? AND id_vehiculo = ? AND fecha_fin IS NULL";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, personaId);
            stmt.setInt(2, vehiculoId);
            int rows = stmt.executeUpdate();

            // Si se actualizo correctamente, confirmo la transaccion
            if (rows > 0) {
                conn.commit(); // Confirmo la transaccion
                return true;
            }

            // Si no se actualizo ninguna fila, rollback no hace nada y vuelve
            conn.rollback();
            return false;

        } catch (SQLException e) {
            System.out.println("Error al actualizar la fecha_fin del historial:" + e.getMessage());
            if (conn != null) {
                conn.rollback(); // Hacemos rollback en caso de error
            }
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }

        }
    }

    public List<String> obtenerModelos() {
        List<String> modelos = new ArrayList<>();
        String sql = "SELECT DISTINCT modelo FROM vehiculo";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establecer la conexión y preparar la consulta
            conn = con.conexion();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                modelos.add(rs.getString("modelo"));  // Agregar cada modelo encontrado
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los modelos: " + e.getMessage());
        } finally {
            // Cierro los recursos
            try {
                if (rs != null) {
                    rs.close();  // Cierro ResultSet
                }
                if (pstmt != null) {
                    pstmt.close();  // Cierro PreparedStatement
                }
                if (con != null) {
                    con.cerrarConexion();  // cierro la conexion
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        return modelos;

    }

    public List<String> obtenerMarcas() {
        List<String> marcas = new ArrayList<>();
        String sql = "SELECT DISTINCT marca FROM vehiculo";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establecer la conexión y preparar la consulta
            conn = con.conexion();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                marcas.add(rs.getString("marca"));  // Agregar cada marca encontrada
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las marcas: " + e.getMessage());
        } finally {
            // Cierro los recursos
            try {
                if (rs != null) {
                    rs.close();  // Cierro ResultSet
                }
                if (pstmt != null) {
                    pstmt.close();  // Cierro PreparedStatement
                }
                if (con != null) {
                    con.cerrarConexion();  // cierro la conexion
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        return marcas;

    }

    public boolean eliminarVehiculo(String matricula) {
        return vehiculoSrv.eliminarVehiculoPorMatri(matricula);

    }

    public boolean actualizarVehiculo(vehiculo objeto) {
        return vehiculoSrv.actualizarVehiculoMatricula(objeto);

    }

}
