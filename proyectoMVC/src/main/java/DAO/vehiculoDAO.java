/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.historial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.vehiculo;

/**
 *
 * @author isaac
 */
public class vehiculoDAO extends genericDAO<vehiculo> {

    conexionBD con = new conexionBD();

    @Override
    public void insertar(vehiculo objeto) {
        String sql = "INSERT INTO vehiculo (matricula, año, marca, modelo) VALUES(?, ?, ?, ?)";

        Connection conexion = null;
        PreparedStatement pstmt = null;

        try {
            conexion = con.conexion();  // Establecer la conexión
            pstmt = conexion.prepareStatement(sql);

            pstmt.setString(1, objeto.getMatricula());
            pstmt.setInt(2, objeto.getAño());
            pstmt.setString(3, objeto.getMarca());
            pstmt.setString(4, objeto.getModelo());

            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Datos ingresados correctamente: " + filasInsertadas);
            }

        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());  // Captura de errores SQL
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();  // Cierro PreparedStatement
                }
                if (conexion != null) {
                    con.cerrarConexion();  // Cierros la conexion
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }

        }
    }

    @Override
    public void borrar(vehiculo objeto) {
        String sql = "DELETE FROM vehiculo WHERE matricula = ?";
        Connection conexion = null;
        PreparedStatement pstmt = null;

        try {
            conexion = con.conexion();  
            pstmt = conexion.prepareStatement(sql);

            pstmt.setString(1, objeto.getMatricula());

            int filasEliminadas = pstmt.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Eliminacion exitosa: " + filasEliminadas);
            } else {
                System.out.println("Ningun vehiculo con matricula especificada encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Error en la eliminacion de vehiculos: " + e.getMessage());  // Captura de errores SQL
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();  // Cierro PreparedStatement
                }
                if (conexion != null) {
                    con.cerrarConexion(); // Cierros la conexion
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }

        }

    }

    @Override
    public List<vehiculo> mostrar() {
        List<vehiculo> listadoVehiculos = new ArrayList<>();
        String sql = "SELECT matricula, año, marca, modelo FROM vehiculo";

        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion();  
            pstmt = conexion.prepareStatement(sql);
            rs = pstmt.executeQuery();  // Ejecuto la consulta

            while (rs.next()) {
                vehiculo v = new vehiculo();
                v.setMatricula(rs.getString("matricula"));
                v.setAño(rs.getInt("año"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));

                listadoVehiculos.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error al mostrar los vehiculos: " + e.getMessage());
        } finally {
            // Cierro los recursos
            try {
                if (rs != null) {
                    rs.close();  // Cierro ResultSet
                }
                if (pstmt != null) {
                    pstmt.close();  // Cierro PreparedStatement
                }
                if (conexion != null) {
                    con.cerrarConexion();  // cierro la conexion
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        return listadoVehiculos;

    }

    @Override
    public void actualizar(vehiculo objeto) {
        String sql = "UPDATE vehiculo SET matricula = ?, año = ?, marca = ?, modelo = ? WHERE matricula = ?";

        Connection conexion = null;
        PreparedStatement pstmt = null;

        try {
            conexion = con.conexion();  
            pstmt = conexion.prepareStatement(sql);

            // Asignar los valores para la actualización
            pstmt.setString(1, objeto.getMatricula());  // Nueva matrícula
            pstmt.setInt(2, objeto.getAño());           // Año
            pstmt.setString(3, objeto.getMarca());      // Marca
            pstmt.setString(4, objeto.getModelo());     // Modelo

            // El ultimo parametro sera la matracula actual para identificar el vehiculo a actualizar
            pstmt.setString(5, objeto.getMatricula());  // Uso la matricula actual para el WHERE

            // Ejecuto la actualizacion
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Actualización exitosa: " + filasActualizadas);
            } else {
                System.out.println("No se actualizó con la matrícula especificada");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar datos: " + e.getMessage());
        } finally {
            // Cierro los recursos
            try {
                if (pstmt != null) {
                    pstmt.close();  // Cierro PreparedStatement
                }
                if (conexion != null) {
                    con.cerrarConexion();  // Cierros la conexion
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

    }

    @Override
    public vehiculo buscarPorId(int id) {
        vehiculo v = null;
        String sql = "SELECT id, matricula, año, marca, modelo FROM vehiculo WHERE id = ?";

        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion();  // Establece la conexion
            pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                v = new vehiculo();
                v.setId(rs.getInt("id"));
                v.setMatricula(rs.getString("matricula"));
                v.setAño(rs.getInt("año"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar por id: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    con.cerrarConexion();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        return v;

    }

    public List<vehiculo> mostrarPorPersonaId(int personaId) {
        List<vehiculo> listaVehiculos = new ArrayList<>();
        String sql = "SELECT id, matricula, año, marca, modelo FROM vehiculo WHERE persona_id = ?";

        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, personaId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                vehiculo v = new vehiculo();
                v.setId(rs.getInt("id"));
                v.setMatricula(rs.getString("matricula"));
                v.setAño(rs.getInt("año"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                listaVehiculos.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar vehiculos por persona_id: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    con.cerrarConexion();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        return listaVehiculos;

    }

    public List<vehiculo> obtVehSinAso(boolean noAsociados) {

        List<vehiculo> lista = new ArrayList<>();
        String sql = noAsociados
                ? "SELECT * FROM vehiculo v LEFT JOIN persona p ON v.persona_id = p.id WHERE v.persona_id IS NULL"
                : "SELECT * FROM vehiculo WHERE persona_id IS NOT NULL";

        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                vehiculo v = new vehiculo();
                v.setMatricula(rs.getString("matricula"));
                v.setAño(rs.getInt("año"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener vehiculo sin asociacion: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    con.cerrarConexion();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        return lista;

    }

    public List<historial> obtHistVeh(String matricula) {

        List<historial> h = new ArrayList<>(); // Lista almacenara el historial de vehiculos

        // Consulta SQL para obtener dni, nombre, fecha_inicio y fecha_fin
        String sql = "SELECT p.dni, p.nombre, h.fecha_inicio, h.fecha_fin FROM historial h "
                + "JOIN persona p ON h.id_persona = p.id "
                + "JOIN vehiculo v ON h.id_vehiculo = v.id "
                + "WHERE v.matricula = ? ORDER BY h.fecha_inicio"; // Filtra por matrícula

        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion(); 
            pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, matricula); // Asigno la matricula al parametro de la consulta
            rs = pstmt.executeQuery(); // Ejecuta la consulta

            // Procesa los resultados de la consulta
            while (rs.next()) {
                // Obtengo los valores de las columnas
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                Date fechaInicio = rs.getDate("fecha_inicio");
                Date fechaFin = rs.getDate("fecha_fin");

                // Crear un objeto historial con los datos obtenidos
                historial histo = new historial(dni, nombre, fechaInicio, fechaFin);
                h.add(histo); // Añade el objeto historial a la lista
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el historial del vehículo: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    con.cerrarConexion();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        return h; // Retorna la lista con el historial

    }

    public int obtVehiIdPorMatri(String matricula) {
        int vehiculoId = -1;
        String sql = "SELECT id FROM vehiculo WHERE matricula = ?";

        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion(); 
            pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, matricula); // Asigna la matricula al parametro de la consulta

            rs = pstmt.executeQuery(); // Ejecuta la consulta
            if (rs.next()) {
                vehiculoId = rs.getInt("id"); // Obtengo el ID del vehiculo
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del vehículo por matrícula: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    con.cerrarConexion();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        return vehiculoId; // Retorna el ID del vehiculo

    }

    public boolean actualizarVehiculoPorMatricula(vehiculo objeto) {
        boolean actualizado = false;
        String sql = "UPDATE vehiculo SET matricula = ?, año = ?, marca = ?, modelo = ? WHERE matricula = ?";

        Connection conexion = null;
        PreparedStatement pstmt = null;

        try {
            conexion = con.conexion(); 
            pstmt = conexion.prepareStatement(sql);

            // parametros en el PreparedStatement
            pstmt.setString(1, objeto.getMatricula());  // Nueva matricula
            pstmt.setInt(2, objeto.getAño());           // Año
            pstmt.setString(3, objeto.getMarca());      // Marca
            pstmt.setString(4, objeto.getModelo());     // Modelo

            //la matrícula actual como condicion para la actualizacion
            pstmt.setString(5, objeto.getMatricula());  // use la matricula actual para el WHERE

            // Ejecutar la actualización
            int filasActualizadas = pstmt.executeUpdate();
            actualizado = filasActualizadas > 0; // Si se actualizo alguna fila

        } catch (SQLException e) {
            System.out.println("Error al actualizar datos: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    con.cerrarConexion();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        return actualizado; // Retorna si se actualizo correctamente

    }

}
