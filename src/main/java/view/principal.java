/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DTO.registroVehiculoDTO;
import controller.personaController;
import controller.vehiculoController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isaac
 */
public class principal extends javax.swing.JFrame {

    private boolean click = false;
    private personaController personaCtrl;
    private vehiculoController vehiculoCtrl;

    private int paginaActual;
    private int totalPaginas;
    private int registrosPorPagina = 10;

    public principal() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Registro de Vehiculos");
        jpFiltrosAvanzados.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        personaCtrl = new personaController();
        cargarDatosTabla();

        String nombre = "";
        String genero = "";
        String marca = "";
        String modelo = "";
        int año = -1;

        totalPaginas = personaCtrl.obtenerTotalPaginas(nombre, genero, marca, modelo, año, 10);
        cargarPagina();

        vehiculoCtrl = new vehiculoController();

        cargarMarcas();
        cargarModelos();

        tblPrincipal.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tblPrincipal.getSelectedRow() != -1) {
                btnHistorial.setEnabled(true);
            } else {
                btnHistorial.setEnabled(false);
            }
        });
        getRootPane().setDefaultButton(btnApliFiltros);

        btnApliFiltros.addActionListener(e -> {
            // Cambiar el color del boton cuando se hace clic
            btnApliFiltros.setBackground(Color.GREEN);

        });

        btnLimpiar.addActionListener(e -> {
            // Restaurar el color original del boton cuando se hace clic o enter en Limpiar
            btnApliFiltros.setBackground(null); // null establece el color predeterminado

        });

        int totalRegsiros = personaCtrl.obtenerTotalRegistrosTablaPrincipal();
        lbRegistros.setText("Total de registros: " + totalRegsiros);

        grupoGenero.add(rdHombre);
        grupoGenero.add(rdMujer);

    }
    ButtonGroup grupoGenero = new ButtonGroup();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        rdHombre = new javax.swing.JRadioButton();
        rdMujer = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        bxMarca = new javax.swing.JComboBox<>();
        btnApliFiltros = new javax.swing.JButton();
        jpFiltros = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrincipal = new javax.swing.JTable();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();
        lbRegistros = new javax.swing.JLabel();
        lbpaginaActual = new javax.swing.JLabel();
        lbTotalDePagina = new javax.swing.JLabel();
        jpFiltrosAvanzados = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        boxModelFil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtFechaMatriculacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNumVehiculos = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuCrearPer = new javax.swing.JMenuItem();
        menuModificarEliminarPer = new javax.swing.JMenuItem();
        menuCrearCoche = new javax.swing.JMenuItem();
        menuModificarEliminarCoche = new javax.swing.JMenuItem();
        menAsociarPerCoch = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 17, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 106;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 18, 0, 0);
        jPanel1.add(txtNombre, gridBagConstraints);

        rdHombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdHombre.setText("Hombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 32, 0);
        jPanel1.add(rdHombre, gridBagConstraints);

        rdMujer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdMujer.setText("Mujer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 32, 0);
        jPanel1.add(rdMujer, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Marca:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 81, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        bxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bxMarcaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 18, 0, 0);
        jPanel1.add(bxMarca, gridBagConstraints);

        btnApliFiltros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnApliFiltros.setText("Aplicar Filtros");
        btnApliFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApliFiltrosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 6, 32, 86);
        jPanel1.add(btnApliFiltros, gridBagConstraints);

        jpFiltros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpFiltrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpFiltrosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpFiltrosMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Filtros avanzados");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpFiltrosLayout = new javax.swing.GroupLayout(jpFiltros);
        jpFiltros.setLayout(jpFiltrosLayout);
        jpFiltrosLayout.setHorizontalGroup(
            jpFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFiltrosLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jpFiltrosLayout.setVerticalGroup(
            jpFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 72, 32, 0);
        jPanel1.add(jpFiltros, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        tblPrincipal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "DNI", "Matricula", "Año", "Marca", "Modelo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPrincipal);
        if (tblPrincipal.getColumnModel().getColumnCount() > 0) {
            tblPrincipal.getColumnModel().getColumn(0).setResizable(false);
            tblPrincipal.getColumnModel().getColumn(1).setResizable(false);
            tblPrincipal.getColumnModel().getColumn(2).setResizable(false);
            tblPrincipal.getColumnModel().getColumn(3).setResizable(false);
            tblPrincipal.getColumnModel().getColumn(4).setResizable(false);
            tblPrincipal.getColumnModel().getColumn(5).setResizable(false);
        }

        btnSiguiente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSiguiente.setText("SIGUIENTE");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAnterior.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAnterior.setText("ANTERIOR");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnHistorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHistorial.setText("HISTORIAL");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        lbRegistros.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbRegistros.setText("REGISTROS");

        lbpaginaActual.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbpaginaActual.setText("jLabel4");

        lbTotalDePagina.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTotalDePagina.setText("jLabel4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAnterior)
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTotalDePagina)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbpaginaActual)
                                .addGap(86, 86, 86)
                                .addComponent(btnSiguiente)
                                .addGap(93, 93, 93)
                                .addComponent(lbRegistros)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpiar)
                                .addGap(143, 143, 143)
                                .addComponent(btnHistorial)))))
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSiguiente)
                            .addComponent(btnAnterior)
                            .addComponent(btnLimpiar)
                            .addComponent(btnHistorial)
                            .addComponent(lbRegistros)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lbpaginaActual)))
                .addGap(18, 18, 18)
                .addComponent(lbTotalDePagina)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Modelo:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Fecha de Matriculacion:");

        txtFechaMatriculacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaMatriculacionKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Nº de Vehiculos:");

        txtNumVehiculos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumVehiculosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jpFiltrosAvanzadosLayout = new javax.swing.GroupLayout(jpFiltrosAvanzados);
        jpFiltrosAvanzados.setLayout(jpFiltrosAvanzadosLayout);
        jpFiltrosAvanzadosLayout.setHorizontalGroup(
            jpFiltrosAvanzadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosAvanzadosLayout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(boxModelFil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFechaMatriculacion, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtNumVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpFiltrosAvanzadosLayout.setVerticalGroup(
            jpFiltrosAvanzadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosAvanzadosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jpFiltrosAvanzadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFiltrosAvanzadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boxModelFil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jpFiltrosAvanzadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFechaMatriculacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(jpFiltrosAvanzadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtNumVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        getContentPane().add(jpFiltrosAvanzados, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Opciones");

        menuCrearPer.setText("Crear Persona");
        menuCrearPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCrearPerActionPerformed(evt);
            }
        });
        jMenu1.add(menuCrearPer);

        menuModificarEliminarPer.setText("Modifcar/Eliminar Persona");
        menuModificarEliminarPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModificarEliminarPerActionPerformed(evt);
            }
        });
        jMenu1.add(menuModificarEliminarPer);

        menuCrearCoche.setText("Crear Coche");
        menuCrearCoche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCrearCocheActionPerformed(evt);
            }
        });
        jMenu1.add(menuCrearCoche);

        menuModificarEliminarCoche.setText("Modificar/Eliminar Coche");
        menuModificarEliminarCoche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModificarEliminarCocheActionPerformed(evt);
            }
        });
        jMenu1.add(menuModificarEliminarCoche);

        menAsociarPerCoch.setText("Asociar Vehiculo");
        menAsociarPerCoch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAsociarPerCochActionPerformed(evt);
            }
        });
        jMenu1.add(menAsociarPerCoch);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuCrearPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCrearPerActionPerformed
        // TODO add your handling code here:
        crearPersona perso = new crearPersona();
        perso.setVisible(true);
    }//GEN-LAST:event_menuCrearPerActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel5MouseClicked

    private void jpFiltrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpFiltrosMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jpFiltrosMouseEntered

    private void jpFiltrosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpFiltrosMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_jpFiltrosMouseExited

    private void jpFiltrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpFiltrosMouseClicked
        // TODO add your handling code here:
        click = !click;
        if (click) {
            jpFiltros.setBackground(Color.GRAY);
            jLabel5.setForeground(Color.WHITE);
        } else {
            jpFiltros.setBackground(null);
            jLabel5.setForeground(null);
        }
        mostrarFiltros();
    }//GEN-LAST:event_jpFiltrosMouseClicked

    private void menuCrearCocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCrearCocheActionPerformed
        // TODO add your handling code here:
        crearCoche coche = new crearCoche();
        coche.setVisible(true);
    }//GEN-LAST:event_menuCrearCocheActionPerformed

    private void menAsociarPerCochActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAsociarPerCochActionPerformed
        // TODO add your handling code here:
        asociarPersYCoche asociar = new asociarPersYCoche();
        asociar.setVisible(true);
    }//GEN-LAST:event_menAsociarPerCochActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        try {
            if (paginaActual > 1) {
                paginaActual--;
                cargarPagina();
                actualizarPaginaYTotal();
            }
        } catch (Exception e) {
            System.out.println("Error en el boton anterior: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        try {
            if (paginaActual < totalPaginas) {
                paginaActual++;
                cargarPagina();
                actualizarPaginaYTotal();
            }
        } catch (Exception e) {
            System.out.println("Error en el boton siguiente: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnApliFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApliFiltrosActionPerformed
            // Obtengo los filtros de los campos de texto y combo box
            String nombre = txtNombre.getText();
            String genero = rdHombre.isSelected() ? "MASCULINO" : (rdMujer.isSelected() ? "FEMENINO" : "");
            String marca = bxMarca.getSelectedItem() != null ? bxMarca.getSelectedItem().toString() : "";
            String modelo = boxModelFil.getSelectedItem() != null ? boxModelFil.getSelectedItem().toString() : "";
            
            if (!verificaPalabras(nombre)) {
                JOptionPane.showMessageDialog(this, "Nombre escrito invalido.", 
                    "Error de Validacion", JOptionPane.ERROR_MESSAGE);
            return;
            }

            // Valido que al menos un filtro este seleccionado
            if (nombre.isEmpty() && genero.isEmpty() && marca.isEmpty() && modelo.isEmpty() && txtFechaMatriculacion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona al menos un filtro antes de aplicar.", "Sin filtros", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Valida el año proporcionado
            int año = -1;
            if (!txtFechaMatriculacion.getText().isEmpty()) {
                año = Integer.parseInt(txtFechaMatriculacion.getText());
                if (año < 2000 || año > 2024) {
                    JOptionPane.showMessageDialog(this, "El año debe estar entre 2000 y 2024.", "Año fuera de rango", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Reiniciar la paginacion al aplicar nuevos filtros
            paginaActual = 1;
            totalPaginas = personaCtrl.obtenerTotalPaginas(nombre, genero, marca, modelo, año, registrosPorPagina);
            cargarPagina();

            // Actualiza la  etiquetas de la interfaz
            actualizarPaginaYTotal();

        
    }//GEN-LAST:event_btnApliFiltrosActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        try {
            // Limpiar todos los campos de texto y selecciones
            txtFechaMatriculacion.setText("");
            txtNumVehiculos.setText("");
            txtNombre.setText("");
            grupoGenero.clearSelection();

            if (boxModelFil.getItemCount() > 0) {
                boxModelFil.setSelectedIndex(0);
            }

            if (bxMarca.getItemCount() > 0) {
                bxMarca.setSelectedIndex(0);
            }

            cargarDatosTabla();
            cargarPagina();

        } catch (Exception e) {
            System.out.println("Error en el botón para Limpiar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void bxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bxMarcaActionPerformed

    }//GEN-LAST:event_bxMarcaActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        String regx = "^[\\p{L} .'-]+$";
        String texto = txtNombre.getText();

        char caracter = evt.getKeyChar();

        if (!Character.toString(caracter).matches(regx)) {
            evt.consume();
        }
        if (txtNombre.getText().length() >= 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtFechaMatriculacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaMatriculacionKeyTyped
        // TODO add your handling code here:
        String regx = "^[0-9]+$";

        char caracter = evt.getKeyChar();

        if (!Character.toString(caracter).matches(regx)) {
            evt.consume();
        }

        if (txtFechaMatriculacion.getText().length() >= 4) {
            evt.consume();

        }
    }//GEN-LAST:event_txtFechaMatriculacionKeyTyped

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tblPrincipal.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String matricula = (String) tblPrincipal.getValueAt(filaSeleccionada, 2);  // Columna de matricula
            historial ventanaHistorial = new historial(matricula);
            ventanaHistorial.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un vehiculo.");
        }
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void txtNumVehiculosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumVehiculosKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean numeros = key >= 40 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
        if (txtNumVehiculos.getText().length() >= 2) {
            evt.consume();

        }

    }//GEN-LAST:event_txtNumVehiculosKeyTyped

    private void menuModificarEliminarCocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModificarEliminarCocheActionPerformed
        // TODO add your handling code here:

        ModificarEliminarCoche vent = new ModificarEliminarCoche();
        vent.setVisible(true);
    }//GEN-LAST:event_menuModificarEliminarCocheActionPerformed

    private void menuModificarEliminarPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModificarEliminarPerActionPerformed
        // TODO add your handling code here:
        ModificarElimnarPer per = new ModificarElimnarPer();
        per.setVisible(true);
    }//GEN-LAST:event_menuModificarEliminarPerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxModelFil;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnApliFiltros;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> bxMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpFiltros;
    private javax.swing.JPanel jpFiltrosAvanzados;
    private javax.swing.JLabel lbRegistros;
    private javax.swing.JLabel lbTotalDePagina;
    private javax.swing.JLabel lbpaginaActual;
    private javax.swing.JMenuItem menAsociarPerCoch;
    private javax.swing.JMenuItem menuCrearCoche;
    private javax.swing.JMenuItem menuCrearPer;
    private javax.swing.JMenuItem menuModificarEliminarCoche;
    private javax.swing.JMenuItem menuModificarEliminarPer;
    private javax.swing.JRadioButton rdHombre;
    private javax.swing.JRadioButton rdMujer;
    private javax.swing.JTable tblPrincipal;
    private javax.swing.JTextField txtFechaMatriculacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumVehiculos;
    // End of variables declaration//GEN-END:variables

    private void mostrarFiltros() {
        jpFiltrosAvanzados.setVisible(!jpFiltrosAvanzados.isVisible());
        revalidate();
        repaint();

    }

    private void cargarDatosTabla() {

        try {
            DefaultTableModel modelo = personaCtrl.obtenerModelPersonaVehiculo();
            tblPrincipal.setModel(modelo);
            tblPrincipal.repaint();
        } catch (Exception e) {
            System.out.println("Error al cargar datos en la tabla: " + e.getMessage());
        }

    }

    private void cargarPagina() {
        try {
            String nombre = txtNombre.getText();
            String genero = rdHombre.isSelected() ? "MASCULINO" : (rdMujer.isSelected() ? "FEMENINO" : "");
            String marca = bxMarca.getSelectedItem() != null ? bxMarca.getSelectedItem().toString() : "";
            String modelo = boxModelFil.getSelectedItem() != null ? boxModelFil.getSelectedItem().toString() : "";

            int año = -1;
            if (!txtFechaMatriculacion.getText().isEmpty()) {
                año = Integer.parseInt(txtFechaMatriculacion.getText());
            }

            int offset = (Math.max(paginaActual, 1) - 1) * registrosPorPagina;

            DefaultTableModel modeloTabla = personaCtrl.obtFilt(nombre, genero, marca, modelo, año, registrosPorPagina, offset);
            tblPrincipal.setModel(modeloTabla);
            tblPrincipal.repaint();

        } catch (Exception e) {
            System.out.println("Error al cargar la pagina: " + e.getMessage());
        }
    }

    public void cargarMarcas() {
        try {
            List<String> marcar = vehiculoCtrl.obtenerMarcas();
            marcar.add(0, "");

            for (String marc : marcar) {
                bxMarca.addItem(marc);
            }
        } catch (Exception e) {
        }

    }

    public void cargarModelos() {
        try {
            List<String> modelos = vehiculoCtrl.obtenerModelos();

            modelos.add(0, "");

            for (String model : modelos) {
                boxModelFil.addItem(model);

            }

        } catch (Exception e) {
            System.out.println("Error al cargar los modelos: " + e.getMessage());
        }

    }

    private void actualizarPaginaYTotal() {
        lbpaginaActual.setText("Página: " + paginaActual);
        lbTotalDePagina.setText("Total Páginas: " + totalPaginas);

        // Habilitar o deshabilitar botones
        btnAnterior.setEnabled(paginaActual > 1);
        btnSiguiente.setEnabled(paginaActual < totalPaginas);
    }
    
    public boolean verificaPalabras(String nombre) {
        //lista de palabras para SQL Malisiosas
        String[] palabrasMalisiosas = {"SELECT", "UPDATE", "INSERT", "DELETE", "DROP", "OR", "CREATE", "=", "*"};

        //Comparacion entre mayusculas y minusculas convertidas
        String nombreMayYMin = nombre.toUpperCase();
        //Verifico si si el contains tiene alguna palabra puesta en el arreglo
        for (String palabra : palabrasMalisiosas) {
            if (nombreMayYMin.contains(palabra)) {
                return false;
            }
        }
        return true;
    }

}
