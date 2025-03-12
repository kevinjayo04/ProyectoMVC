/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.conexionBD;
import DAO.personaDAO;
import DAO.vehiculoDAO;
import DTO.registroVehiculoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.persona;
import model.vehiculo;

/**
 *
 * @author isaac
 */
public class personaService {

    conexionBD con = new conexionBD();
    private personaDAO personaDao;
    private vehiculoDAO vehiculoDao;

    public personaService() {
        personaDao = new personaDAO();
        vehiculoDao = new vehiculoDAO();
    }

    public List<registroVehiculoDTO> obtenerPersonaConVehiculo() {
        List<registroVehiculoDTO> listaCombinada = new ArrayList<>();

        try {
            for (persona p : personaDao.mostrar()) {
                for (vehiculo v : vehiculoDao.mostrarPorPersonaId(p.getId())) {
                    registroVehiculoDTO dto = new registroVehiculoDTO();
                    dto.setNombre(p.getNombre());
                    dto.setDni(p.getDni());
                    dto.setMatricula(v.getMatricula());
                    dto.setAño(v.getAño());
                    dto.setMarca(v.getMarca());
                    dto.setModelo(v.getModelo());
                    listaCombinada.add(dto);

                }

            }

        } catch (Exception e) {
            System.out.println("Error al obtener las personas con vehiculos: " + e.getMessage());
        }

        return listaCombinada;

    }

    public List<Object[]> obtenerPagina(int numeroPagina) {
        List<Object[]> listadoPaginada = new ArrayList<>();
        int registroPorPagina = 10;
        int offset = numeroPagina * registroPorPagina;

        String sql = "SELECT p.nombre, p.dni, v.matricula, v.año, v.marca, v.modelo "
                + "FROM persona p JOIN vehiculo v ON p.id = v.persona_id "
                + "LIMIT ? OFFSET ?";

        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);

            // Establezco los parametros de la consulta
            pstmt.setInt(1, registroPorPagina);  // Numero de registros por pagina
            pstmt.setInt(2, offset);  // Desplazamiento para la paginacion

            // Ejecuto la consulta
            rs = pstmt.executeQuery();

            // Proceso los resultados
            while (rs.next()) {
                Object[] fila = {
                    rs.getString("nombre"),
                    rs.getString("dni"),
                    rs.getString("matricula"),
                    rs.getInt("año"),
                    rs.getString("marca"),
                    rs.getString("modelo")
                };
                listadoPaginada.add(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos paginados: " + e.getMessage());
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

        return listadoPaginada;

    }

    public int obtenerTotalRegistros(String nombre, String genero, String marca, String modelo, int año) {
        String sql = "SELECT COUNT(*) FROM persona p "
                + "JOIN vehiculo v ON p.id = v.persona_id WHERE 1=1 ";

        List<Object> parametrosFil = new ArrayList<>();
        
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalRegistros = 0;

        if (nombre != null && !nombre.isEmpty()) {
            sql += "AND p.nombre LIKE ? ";
            parametrosFil.add("%" + nombre + "%");
        }
        if (genero != null && !genero.isEmpty()) {
            sql += "AND p.genero = ? ";
            parametrosFil.add(genero);
        }
        if (marca != null && !marca.isEmpty()) {
            sql += "AND v.marca = ? ";
            parametrosFil.add(marca);
        }
        if (modelo != null && !modelo.isEmpty()) {
            sql += "AND v.modelo = ? ";
            parametrosFil.add(modelo);
        }
        if (año != -1) {
            sql += "AND v.año = ? ";
            parametrosFil.add(año);
        }

        try {
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);

            int index = 1;
            for (Object parametro : parametrosFil) {
                pstmt.setObject(index++, parametro);
            }

            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalRegistros = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el total de registros: " + e.getMessage());
        } finally {
            // Cierro los recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return totalRegistros;
    }

    public List<Object[]> obtenerFiltrado(String nombre, String genero, String marca, String modelo, int año, int limit, int offset) {
        List<Object[]> listaFiltrada = new ArrayList<>();

        String sql = "SELECT p.nombre, p.dni, v.matricula, v.año, v.marca, v.modelo "
                + "FROM persona p "
                + "JOIN vehiculo v ON p.id = v.persona_id WHERE 1=1  ";

        List<Object> parametrosFil = new ArrayList<>();

        if (nombre != null && !nombre.isEmpty()) {
            sql += "AND p.nombre LIKE ? ";
            parametrosFil.add("%" + nombre + "%");
        }

        if (genero != null && !genero.isEmpty()) {
            sql += "AND p.genero = ? ";
            parametrosFil.add(genero);
        }

        if (marca != null && !marca.isEmpty()) {
            sql += "AND v.marca = ? ";
            parametrosFil.add(marca);
        }

        if (modelo != null && !modelo.isEmpty()) {
            sql += "AND v.modelo = ? ";
            parametrosFil.add(modelo);
        }

        if (año != -1) {
            sql += "AND v.año = ? ";
            parametrosFil.add(año);
        }

        sql += "LIMIT ? OFFSET ?";

        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion();

            // Preparo la consulta
            pstmt = conexion.prepareStatement(sql);

            // Establezco los parametros
            int index = 1;
            for (Object parametro : parametrosFil) {
                pstmt.setObject(index++, parametro);
            }

            pstmt.setInt(index++, limit);
            pstmt.setInt(index, offset);

            // Ejecuto la consulta
            rs = pstmt.executeQuery();

            // Proceso los resultados
            while (rs.next()) {
                Object[] fila = {
                    rs.getString("nombre"),
                    rs.getString("dni"),
                    rs.getString("matricula"),
                    rs.getInt("año"),
                    rs.getString("marca"),
                    rs.getString("modelo")
                };
                listaFiltrada.add(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el filtrado: " + e.getMessage());
        } finally {
            // Cierro los recursos manualmente
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return listaFiltrada;

    }

    public List<persona> obtenerPersonas() {
        return personaDao.mostrar();

    }

    public List<persona> obtPersConAso(boolean noAsociado) {
        return personaDao.obtPerAso(noAsociado);
    }

    public void eliminarPer(persona p) {
        personaDao.borrar(p);
    }

    public boolean verificarAsociacionVehiculo(String dni) {
        return personaDao.existeAsociacionVehiculo(dni);
    }

    public void actualizarPersona(persona perActualizae) {
        personaDao.actualizar(perActualizae);
    }

}
