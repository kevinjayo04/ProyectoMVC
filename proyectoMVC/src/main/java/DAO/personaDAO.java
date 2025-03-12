/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.persona;

/**
 *
 * @author isaac
 */
public class personaDAO extends genericDAO<persona> {

    conexionBD con = new conexionBD();

    @Override
    public void insertar(persona objeto) {

        String sql = "INSERT INTO persona (dni, nombre, genero) VALUES (?, ?, ?)";

        Connection conexion = null;
        PreparedStatement pstmt = null;

        try {
            conexion = con.conexion();

            // Preparo el SQL
            pstmt = conexion.prepareStatement(sql);

            // Establezco los parametros
            pstmt.setString(1, objeto.getDni());
            pstmt.setString(2, objeto.getNombre());
            pstmt.setString(3, objeto.getGenero().name());

            // Ejecuto la insercion
            pstmt.executeUpdate();

            System.out.println("Persona ingresada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar persona: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();  // Cierro el PreparedStatement
                }

                if (conexion != null) {
                    con.cerrarConexion();// Cierro la conexión
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }

        }

    }

    @Override
    public void borrar(persona objeto) {
        String sql = "DELETE FROM persona WHERE dni = ?";

        Connection conexion = null;
        PreparedStatement pstmt = null;

        try {
            conexion = con.conexion();

            // Preparo el SQL
            pstmt = conexion.prepareStatement(sql);

            // Establezco el parametro (dni)
            pstmt.setString(1, objeto.getDni());

            // Ejecuto la eliminacion
            int filasEliminadas = pstmt.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Eliminacion exitosa: " + filasEliminadas);
            } else {
                System.out.println("No se encontro ninguna persona con el DNI especificado: " + objeto.getDni());
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar persona con dni especificado: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();  // Cierro el PreparedStatement
                }

                if (conexion != null) {
                    con.cerrarConexion();  // Cierro la conexion
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }

        }

    }

    @Override
    public List<persona> mostrar() {
        List<persona> listadoPersonas = new ArrayList<>();
        String sql = "SELECT dni, nombre, genero FROM persona";

        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establezco la conexion y preparo la consulta
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Proceso el resultados
            while (rs.next()) {
                persona p = new persona();
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("nombre"));

                // Determino el genero
                p.setGenero(rs.getString("genero").equalsIgnoreCase("MASCULINO") ? persona.genero.MASCULINO : persona.genero.FEMENINO);

                listadoPersonas.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las personas: " + e.getMessage());
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

        return listadoPersonas;

    }

    @Override
    public void actualizar(persona objeto) {
        String sql = "UPDATE persona SET dni = ?, nombre = ?, genero = ? WHERE dni = ?";

        Connection conexion = null;
        PreparedStatement pstmt = null;

        try {
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);

            // Establecemos los valores de actualizar
            pstmt.setString(1, objeto.getDni());  // Nuevo DNI 
            pstmt.setString(2, objeto.getNombre()); // Nuevo nombre
            pstmt.setString(3, objeto.getGenero().toString()); // Nuevo genero
            pstmt.setString(4, objeto.getDni());  // Condición para actualizar por DNI

            // Ejecutamos la actualizacion
            int filasActualizadas = pstmt.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Actualizacion exitosa de " + filasActualizadas + " fila(s).");
            } else {
                System.out.println("No se encontro ninguna persona con el DNI especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la actualizacion de datos: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();  // Cierro el PreparedStatement
                }

                if (conexion != null) {
                    con.cerrarConexion();  // Cierro la conexion
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }

        }

    }

    @Override
    public persona buscarPorId(int id) {
        persona p = null;
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT id, dni, nombre, genero FROM persona WHERE id = ?";

        try {
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, id);

            // Ejecuto la consulta y proceso los resultados
            rs = pstmt.executeQuery();
            if (rs.next()) {
                p = new persona();
                p.setId(rs.getInt("id"));
                p.setDni(rs.getString("dni"));
                p.setNombre(rs.getString("nombre"));

                // Proceso el genero
                String genero = rs.getString("genero");
                if ("MASCULINO".equalsIgnoreCase(genero)) {
                    p.setGenero(persona.genero.MASCULINO);
                } else if ("FEMENINO".equalsIgnoreCase(genero)) {
                    p.setGenero(persona.genero.FEMENINO);
                } else {
                    p.setGenero(null);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar persona por ID: " + e.getMessage());
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

        return p;

    }

    public List<persona> obtPerAso(boolean noAsociados) {
        List<persona> lista = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = noAsociados
                ? "SELECT * FROM persona p LEFT JOIN vehiculo v ON p.id = v.persona_id WHERE v.persona_id IS NULL"
                : "SELECT DISTINCT p.* FROM persona p JOIN vehiculo v ON p.id = v.persona_id";

        try {
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);

            // Ejecuto la consulta y proceso los resultados
            rs = pstmt.executeQuery();
            while (rs.next()) {
                persona p = new persona();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDni(rs.getString("dni"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener personas con/sin asociados: " + e.getMessage());
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

        return lista;

    }

    public int obtPersIdporDNI(String dni) {

        int personaId = -1;
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT id FROM persona WHERE dni = ?";

        try {
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, dni);  // Asigno el DNI al parametro

            // Ejecuto la consulta
            rs = pstmt.executeQuery();
            if (rs.next()) {
                personaId = rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el id de la persona por DNI: " + e.getMessage());
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

        return personaId;

    }

    public boolean existeAsociacionVehiculo(String dni) {
        String sql = "SELECT COUNT(*) FROM historial WHERE id_persona = (SELECT id FROM persona WHERE dni = ?)";
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, dni);  // Asigno el DNI al parametro

            // Ejecuto la consulta
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si hay asociaciones
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar la asociacion de vehiculos: " + e.getMessage());
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

        return false;

    }

    public boolean existeDNI(String dni) {
        String sql = "SELECT COUNT(*) FROM persona WHERE dni = ?";
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion(); 
            pstmt = conexion.prepareStatement(sql); 
            pstmt.setString(1, dni); 

            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar el DNI en la base de datos: " + e.getMessage());
        } finally {
            // Cerrar los recursos
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
        return false; 
    }

}
