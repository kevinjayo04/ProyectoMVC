/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.conexionBD;
import DAO.personaDAO;
import DTO.registroVehiculoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.persona;
import service.personaService;

/**
 *
 * @author isaac
 */
public class personaController {

    private personaService personaSrv;
    private personaDAO personaDao;
    private conexionBD con;

    public personaController() {
        personaSrv = new personaService();
        personaDao = new personaDAO();
        con = new conexionBD();

    }

    public DefaultTableModel obtenerModelPersonaVehiculo() {
        String[] columnas = {"Nombre", "DNI", "Matricula", "Año", "Marca", "Modelo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        try {
            List<registroVehiculoDTO> lista = personaSrv.obtenerPersonaConVehiculo();
            for (registroVehiculoDTO dto : lista) {
                Object[] fila = {dto.getNombre(), dto.getDni(), dto.getMatricula(), dto.getAño(), dto.getMarca(), dto.getModelo()};
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            System.out.println("Error al obtener el modelo de perdonas con vehiculo: " + e.getMessage());

        }
        return modelo;

    }

    public DefaultTableModel obtenerModeloPaginado(int numeroPagina) {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Nombre", "DNI", "Matrícula", "Año", "Marca", "Modelo"}, 0);
        try {
            List<Object[]> datosPaginados = personaSrv.obtenerPagina(numeroPagina);
            for (Object[] fila : datosPaginados) {
                modelo.addRow(fila);

            }
        } catch (Exception e) {
            System.out.println("Error en la obtencion del modelo de paginado: " + e.getMessage());
        }
        return modelo;
    }

    public int obtenerTotalPaginas(String nombre, String genero, String marca, String modelo, int año, int registrosPorPagina) {
        // Obtengo el total de registros que coinciden con los filtros
        int totalRegistros = personaSrv.obtenerTotalRegistros(nombre, genero, marca, modelo, año);
        // Calculo el numero total de paginas
        return (int) Math.ceil((double) totalRegistros / registrosPorPagina);
    }

    public DefaultTableModel obtFilt(String nombre, String genero, String marca, String modelo, int año, int limit, int offset) {
        String[] columnas = {"Nombre", "DNI", "Matrícula", "Año", "Marca", "Modelo"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        try {
            List<Object[]> personaFiltrada = personaSrv.obtenerFiltrado(nombre, genero, marca, modelo, año, limit, offset);
            for (Object[] fila : personaFiltrada) {
                model.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el modelo filtrado por los parámetros: " + e.getMessage());
        }

        return model;
    }

    public void registroPersona(String dni, String nombre, String genero) {
        persona per = new persona(dni, nombre, genero);
        try {
            personaDao.insertar(per);
            System.out.println("persona insertado con exito ");
        } catch (Exception e) {
            System.out.println("Error al registro de persona: " + e.getMessage());
        }

    }

    public DefaultTableModel cargarPersonasTb() {
        String[] columna = {"Nombre", "DNI"};
        DefaultTableModel modelo = new DefaultTableModel(columna, 0);

        try {
            List<persona> listadoPersonas = personaSrv.obtenerPersonas();

            for (persona p : listadoPersonas) {
                Object[] fila = {p.getNombre(), p.getDni()};
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar las personas en la tabla: " + e.getMessage());
        }
        return modelo;

    }

    public DefaultTableModel cargarPersonasTbConGenero() {
        String[] columna = {"DNI", "Nombre", "Genero"}; // Añado la columna de genero
        DefaultTableModel modelo = new DefaultTableModel(columna, 0);

        try {
            List<persona> listadoPersonas = personaSrv.obtenerPersonas();

            for (persona p : listadoPersonas) {
                // Obtengo el genero de la persona y lo agrego a la fila
                String genero = p.getGenero().toString();
                Object[] fila = {p.getDni(), p.getNombre(), genero};
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar las personas en la tabla: " + e.getMessage());
        }
        return modelo;
    }

    public DefaultTableModel obtPerAso(boolean mostrarNoAsociados) {
        String[] columna = {"Nombre", "DNI"};
        DefaultTableModel modelo = new DefaultTableModel(columna, 0);
        try {
            List<persona> personas = personaSrv.obtPersConAso(mostrarNoAsociados);

            for (persona p : personas) {
                modelo.addRow(new Object[]{p.getNombre(), p.getDni()});
            }

        } catch (Exception e) {
            System.out.println("Error al obtener personas segun asociacion: " + e.getMessage());
        }
        return modelo;
    }

    public void eliminarPerSeleccionada(String dniPersona) {
        persona personaEliminar = new persona();
        personaEliminar.setDni(dniPersona);

        personaSrv.eliminarPer(personaEliminar);

    }

    public DefaultTableModel recargaPersonasTb() {
        String[] columna = {"DNI", "Nombre", "Genero"};
        DefaultTableModel modelo = new DefaultTableModel(columna, 0);

        try {
            List<persona> listadoPersonas = personaSrv.obtenerPersonas();

            for (persona p : listadoPersonas) {
                Object[] fila = {p.getDni(), p.getNombre(), p.getGenero()};
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar las personas en la tabla: " + e.getMessage());
        }
        return modelo;
    }

    public boolean tieneVehiculoAsociado(String dni) {
        // Verifico en el servicio si la persona tiene vehiculos asociados
        return personaSrv.verificarAsociacionVehiculo(dni);
    }

    public void actualizarPersonaSeleccionada(persona personaActualizar) {
        // llamo al metodo del servicio que hace la actualizacion
        personaSrv.actualizarPersona(personaActualizar);
    }

    public int obtenerTotalRegistrosTablaPrincipal() {
        String sql = "SELECT COUNT(*) FROM persona p JOIN vehiculo v ON p.id = v.persona_id";
        int totalRegistros = 0;
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = con.conexion();
            pstmt = conexion.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                totalRegistros = rs.getInt(1); // Obtengo el total de registros
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el total de registros de la tabla principal: " + e.getMessage());
        } finally {
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
        return totalRegistros;
    }

    public boolean existeDNI(String dni) {
        return personaDao.existeDNI(dni);
    }
}
