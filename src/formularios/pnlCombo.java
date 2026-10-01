package formularios;

import clases.Combo;
import clases.ComboDAO;
import clases.Detalle;
import clases.DetalleDAO;
import clases.Producto;
import clases.ProductoDAO;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class pnlCombo extends javax.swing.JPanel {

    DefaultTableModel modeloDetalle;
    DefaultTableModel modeloProducto;
    ComboDAO comboDAO;
    DetalleDAO detalleDAO;
    private javax.swing.JButton btnVerCombos;

    public pnlCombo() {
        btnVerCombos = new javax.swing.JButton();
        btnVerCombos.setText("Ver Combos");
        btnVerCombos.setBackground(new Color(0, 149, 183));
        btnVerCombos.setForeground(Color.WHITE);
        btnVerCombos.setPreferredSize(new java.awt.Dimension(120, 35));
        btnVerCombos.addActionListener(e -> {
            formularios.frmCombosRecientes dialog = new formularios.frmCombosRecientes(null);
            dialog.setVisible(true);
            int idSeleccion = dialog.getComboSeleccionado();
            if (idSeleccion != -1) {
                ComboDAO dao = new ComboDAO();
                List<Combo> lista = dao.listarCombos();
                for (Combo c : lista) {
                    if (c.getIdCombo() == idSeleccion) {
                        txtIdCombo.setText(String.valueOf(c.getIdCombo()));
                        txtCombo.setText(c.getCombo());
                        txtPrecioCombo.setText(String.valueOf(c.getPrecio()));
                        cargarTablaDetalles(c.getIdCombo());
                        break;
                    }
                }
            }
        });
        initComponents();
        ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/logoComboFer.png"));
        Image imagen = icono.getImage();
        Image imagenEscalada = imagen.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(imagenEscalada));
        Color azulClaro = new Color(234, 244, 248);
        tblProductos.getParent().setBackground(azulClaro);
        tblProductos.getTableHeader().setBackground(azulClaro);
        tblDetalle.getParent().setBackground(azulClaro);
        tblDetalle.getTableHeader().setBackground(azulClaro);
        llenarTabla();
        crearDetalle();
        txtTotal.setText("0.0");
        comboDAO = new ComboDAO();
        detalleDAO = new DetalleDAO();
        cargarComboBox();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSuperior = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        pnlCampos = new javax.swing.JPanel();
        lblIdCombo = new javax.swing.JLabel();
        txtIdCombo = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        lblCombo = new javax.swing.JLabel();
        txtCombo = new javax.swing.JTextField();
        lblEncabezado = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        pnlBotones = new javax.swing.JPanel();
        btnlimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        pnlComboSelector = new javax.swing.JPanel();
        lblCombos = new javax.swing.JLabel();
        cmbCombo = new javax.swing.JComboBox();
        pnlCentro = new javax.swing.JPanel();
        pnlProductos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        pnlDetalleSeccion = new javax.swing.JPanel();
        lblDetalle = new javax.swing.JLabel();
        pnlDetalleInferior = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        pnlTotales = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecioCombo = new javax.swing.JTextField();
        btnEliminarFila = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(900, 550));
        setPreferredSize(new java.awt.Dimension(900, 550));
        setLayout(new java.awt.BorderLayout());

        pnlSuperior.setBackground(new java.awt.Color(234, 244, 248));
        pnlSuperior.setLayout(new java.awt.BorderLayout(10, 0));

        lblLogo.setPreferredSize(new java.awt.Dimension(130, 120));
        lblLogo.setVerifyInputWhenFocusTarget(false);
        pnlSuperior.add(lblLogo, java.awt.BorderLayout.WEST);

        pnlCampos.setBackground(new java.awt.Color(234, 244, 248));
        pnlCampos.setLayout(new java.awt.GridBagLayout());

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(3, 5, 3, 5);
        gbc.anchor = java.awt.GridBagConstraints.WEST;

        lblIdCombo.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblIdCombo.setText("Id de combo");
        gbc.gridx = 0; gbc.gridy = 0;
        pnlCampos.add(lblIdCombo, gbc);

        txtIdCombo.setEditable(false);
        txtIdCombo.setPreferredSize(new java.awt.Dimension(60, 25));
        gbc.gridx = 1; gbc.gridy = 0;
        pnlCampos.add(txtIdCombo, gbc);

        btnNuevo.setBackground(new java.awt.Color(0, 149, 183));
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png")));
        btnNuevo.setText("Nuevo Combo");
        btnNuevo.setPreferredSize(new java.awt.Dimension(140, 35));
        gbc.gridx = 2; gbc.gridy = 0;
        pnlCampos.add(btnNuevo, gbc);

        lblCombo.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblCombo.setText("Nombre del combo");
        gbc.gridx = 0; gbc.gridy = 1;
        pnlCampos.add(lblCombo, gbc);

        txtCombo.setPreferredSize(new java.awt.Dimension(250, 25));
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        pnlCampos.add(txtCombo, gbc);
        gbc.gridwidth = 1;

        lblEncabezado.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblEncabezado.setForeground(new java.awt.Color(0, 149, 183));
        lblEncabezado.setText("Seleccione uno por uno los productos del combo");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        pnlCampos.add(lblEncabezado, gbc);
        gbc.gridwidth = 1;

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblBuscar.setText("Buscar por nombre");
        gbc.gridx = 0; gbc.gridy = 3;
        pnlCampos.add(lblBuscar, gbc);

        txtBuscar.setPreferredSize(new java.awt.Dimension(200, 25));
        gbc.gridx = 1; gbc.gridy = 3;
        pnlCampos.add(txtBuscar, gbc);

        pnlSuperior.add(pnlCampos, java.awt.BorderLayout.CENTER);

        pnlBotones.setBackground(new java.awt.Color(234, 244, 248));
        pnlBotones.setLayout(new java.awt.GridLayout(3, 2, 8, 8));
        pnlBotones.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        btnlimpiar.setBackground(new java.awt.Color(200, 230, 235));
        btnlimpiar.setForeground(new java.awt.Color(10, 50, 60));
        btnlimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png")));
        btnlimpiar.setText("Limpiar");
        pnlBotones.add(btnlimpiar);

        btnEliminar.setBackground(new java.awt.Color(200, 230, 235));
        btnEliminar.setForeground(new java.awt.Color(10, 50, 60));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png")));
        btnEliminar.setText("Eliminar Combo");
        pnlBotones.add(btnEliminar);

        btnActualizar.setBackground(new java.awt.Color(0, 149, 183));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png")));
        btnActualizar.setText("Actualizar combo");
        pnlBotones.add(btnActualizar);

        btnGuardar.setBackground(new java.awt.Color(0, 149, 183));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png")));
        btnGuardar.setText("Guardar Combo");
        pnlBotones.add(btnGuardar);

        pnlBotones.add(btnVerCombos);

        pnlBotones.setPreferredSize(new java.awt.Dimension(300, 0));
        pnlSuperior.add(pnlBotones, java.awt.BorderLayout.EAST);

        pnlComboSelector.setBackground(new java.awt.Color(234, 244, 248));
        lblCombos.setText("Seleccione un combo");
        pnlComboSelector.add(lblCombos);

        cmbCombo.setBackground(new java.awt.Color(200, 230, 235));
        cmbCombo.setPreferredSize(new java.awt.Dimension(150, 25));
        pnlComboSelector.add(cmbCombo);

        pnlSuperior.add(pnlComboSelector, java.awt.BorderLayout.SOUTH);

        add(pnlSuperior, java.awt.BorderLayout.NORTH);

        pnlCentro.setBackground(new java.awt.Color(234, 244, 248));
        pnlCentro.setLayout(new java.awt.BorderLayout(0, 5));

        pnlProductos.setBackground(new java.awt.Color(234, 244, 248));
        pnlProductos.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos disponibles"));
        pnlProductos.setLayout(new java.awt.BorderLayout());

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {}
        ));
        jScrollPane1.setViewportView(tblProductos);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 180));

        pnlProductos.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        pnlCentro.add(pnlProductos, java.awt.BorderLayout.NORTH);

        pnlDetalleSeccion.setBackground(new java.awt.Color(234, 244, 248));
        pnlDetalleSeccion.setLayout(new java.awt.BorderLayout(0, 5));

        lblDetalle.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblDetalle.setForeground(new java.awt.Color(0, 149, 183));
        lblDetalle.setText("Su combo tiene los siguientes productos");
        pnlDetalleSeccion.add(lblDetalle, java.awt.BorderLayout.NORTH);

        pnlDetalleInferior.setBackground(new java.awt.Color(234, 244, 248));
        pnlDetalleInferior.setLayout(new java.awt.BorderLayout(10, 0));

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {}
        ));
        jScrollPane3.setViewportView(tblDetalle);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(500, 170));

        pnlDetalleInferior.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        pnlTotales.setBackground(new java.awt.Color(234, 244, 248));
        pnlTotales.setLayout(new java.awt.GridBagLayout());

        java.awt.GridBagConstraints gbc2 = new java.awt.GridBagConstraints();
        gbc2.insets = new java.awt.Insets(5, 5, 5, 5);
        gbc2.anchor = java.awt.GridBagConstraints.WEST;

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblTotal.setText("Total en productos $");
        gbc2.gridx = 0; gbc2.gridy = 0;
        pnlTotales.add(lblTotal, gbc2);

        txtTotal.setEditable(false);
        txtTotal.setPreferredSize(new java.awt.Dimension(80, 25));
        gbc2.gridx = 1; gbc2.gridy = 0;
        pnlTotales.add(txtTotal, gbc2);

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblPrecio.setText("Precio");
        gbc2.gridx = 0; gbc2.gridy = 1;
        pnlTotales.add(lblPrecio, gbc2);

        txtPrecioCombo.setPreferredSize(new java.awt.Dimension(80, 25));
        gbc2.gridx = 1; gbc2.gridy = 1;
        pnlTotales.add(txtPrecioCombo, gbc2);

        btnEliminarFila.setBackground(new java.awt.Color(200, 230, 235));
        btnEliminarFila.setForeground(new java.awt.Color(10, 50, 60));
        btnEliminarFila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png")));
        btnEliminarFila.setText("Eliminar fila");
        gbc2.gridx = 0; gbc2.gridy = 2; gbc2.gridwidth = 2;
        pnlTotales.add(btnEliminarFila, gbc2);

        pnlDetalleInferior.add(pnlTotales, java.awt.BorderLayout.EAST);

        pnlDetalleSeccion.add(pnlDetalleInferior, java.awt.BorderLayout.CENTER);
        pnlCentro.add(pnlDetalleSeccion, java.awt.BorderLayout.CENTER);

        add(pnlCentro, java.awt.BorderLayout.CENTER);

        txtIdCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar())) evt.consume();
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (Character.isDigit(c)) { evt.consume(); getToolkit().beep(); }
            }
        });

        txtCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (Character.isDigit(c)) { evt.consume(); getToolkit().beep(); }
            }
        });

        txtPrecioCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                String t = txtPrecioCombo.getText();
                if (!Character.isDigit(c) && c != '.') { evt.consume(); getToolkit().beep(); }
                if (c == '.' && t.contains(".")) { evt.consume(); getToolkit().beep(); }
            }
        });

        btnNuevo.addActionListener(this::btnNuevoActionPerformed);
        btnlimpiar.addActionListener(this::btnlimpiarActionPerformed);
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        btnEliminarFila.addActionListener(this::btnEliminarFilaActionPerformed);
        cmbCombo.addItemListener(this::cmbComboItemStateChanged);

        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });

        tblDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleMouseClicked(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtIdCombo.getText().isEmpty()) {
            ComboDAO dao = new ComboDAO();
            int id = dao.obtenerIdCombo();
            txtIdCombo.setText(String.valueOf(id + 1));
            txtCombo.requestFocus();
        }
    }

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        txtIdCombo.setText("");
        txtCombo.setText("");
        txtPrecioCombo.setText("");
        txtTotal.setText("0.0");
        modeloDetalle.setRowCount(0);
    }

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {
        String texto = txtBuscar.getText();
        ProductoDAO dao = new ProductoDAO();
        List<Producto> lista;
        if (texto.isEmpty()) {
            lista = dao.listar();
        } else {
            lista = dao.buscarProductos(texto);
        }
        llenarTabla(lista);
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        ComboDAO dao = new ComboDAO();
        if (txtIdCombo.getText().trim().isEmpty()) {
            txtIdCombo.requestFocus();
            return;
        }
        try {
            int idCombo = Integer.parseInt(txtIdCombo.getText());
            if (dao.eliminar(idCombo)) {
                JOptionPane.showMessageDialog(null, "Eliminado correctamente el combo");
                txtIdCombo.setText("");
                txtCombo.setText("");
                txtPrecioCombo.setText("");
                txtTotal.setText("0.0");
                modeloDetalle.setRowCount(0);
                cargarComboBox();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Id de combo inválido");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 1) {
            int fila = tblProductos.getSelectedRow();
            if (fila >= 0) {
                int id;
                double precio;
                try {
                    id = Integer.parseInt(tblProductos.getValueAt(fila, 0).toString());
                    precio = Double.parseDouble(tblProductos.getValueAt(fila, 3).toString());
                } catch (NumberFormatException ex) {
                    return;
                }
                String nombre = tblProductos.getValueAt(fila, 1).toString();
                agregarProductoDetalle(id, nombre, precio);
            }
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        Combo comb = new Combo();
        if (txtIdCombo.getText().trim().isEmpty()) { txtIdCombo.requestFocus(); return; }
        if (txtCombo.getText().trim().isEmpty()) { txtCombo.requestFocus(); return; }
        if (txtTotal.getText().trim().isEmpty()) { txtTotal.requestFocus(); return; }
        if (txtPrecioCombo.getText().trim().isEmpty()) { txtPrecioCombo.requestFocus(); return; }
        if (modeloDetalle.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún producto para el combo");
            return;
        }
        int idCombo;
        String nombreCombo = txtCombo.getText();
        double precioCombo;
        double total;
        try {
            idCombo = Integer.parseInt(txtIdCombo.getText());
            precioCombo = Double.parseDouble(txtPrecioCombo.getText());
            total = Double.parseDouble(txtTotal.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Verifique que los campos numéricos sean válidos");
            return;
        }
        if (precioCombo > total) {
            JOptionPane.showMessageDialog(null, "El precio del combo no puede ser mayor al total calculado");
        } else {
            comb.setIdCombo(idCombo);
            comb.setCombo(nombreCombo);
            comb.setPrecio(precioCombo);
            comboDAO.actualizarCombo(comb);
            detalleDAO.eliminarPorCombo(idCombo);
            List<Detalle> listaDetalle = obtenerDetalleDesdeTabla();
            detalleDAO.guardarDetalle(idCombo, listaDetalle);
            cargarComboBox();
        }
        JOptionPane.showMessageDialog(null, "Combo actualizado con éxito");
    }

    private void cmbComboItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (cmbCombo.getSelectedItem() != null) {
                Combo comboSeleccionado = (Combo) cmbCombo.getSelectedItem();
                txtIdCombo.setText(String.valueOf(comboSeleccionado.getIdCombo()));
                txtCombo.setText(comboSeleccionado.getCombo());
                txtPrecioCombo.setText(String.valueOf(comboSeleccionado.getPrecio()));
                cargarTablaDetalles(comboSeleccionado.getIdCombo());
            }
        }
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtIdCombo.getText().trim().isEmpty()) { txtIdCombo.requestFocus(); return; }
        if (txtCombo.getText().trim().isEmpty()) { txtCombo.requestFocus(); return; }
        if (txtTotal.getText().trim().isEmpty()) { txtTotal.requestFocus(); return; }
        if (txtPrecioCombo.getText().trim().isEmpty()) { txtPrecioCombo.requestFocus(); return; }
        if (modeloDetalle.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún producto para el combo");
            return;
        }
        int idCombo;
        String nombreCombo = txtCombo.getText();
        double precioCombo;
        double total;
        try {
            idCombo = Integer.parseInt(txtIdCombo.getText());
            precioCombo = Double.parseDouble(txtPrecioCombo.getText());
            total = Double.parseDouble(txtTotal.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Verifique que los campos numéricos sean válidos");
            return;
        }
        if (precioCombo > total) {
            JOptionPane.showMessageDialog(null, "El precio del combo no puede ser mayor al total calculado");
        } else {
            int resultado = comboDAO.guardarCombo(idCombo, nombreCombo, precioCombo);
            if (resultado > 0) {
                List<Detalle> listaDetalle = obtenerDetalleDesdeTabla();
                detalleDAO.guardarDetalle(idCombo, listaDetalle);
            }
            JOptionPane.showMessageDialog(null, "Combo guardado con éxito");
            cargarComboBox();
        }
    }

    private void btnEliminarFilaActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tblDetalle.getSelectedRow();
        if (fila >= 0) {
            modeloDetalle.removeRow(fila);
            calcularTotal();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
        }
    }

    private void tblDetalleMouseClicked(java.awt.event.MouseEvent evt) {
    }

    private void calcularTotal() {
        double total = 0;
        for (int fila = 0; fila < modeloDetalle.getRowCount(); fila++) {
            Object subTotal = modeloDetalle.getValueAt(fila, 4);
            if (subTotal != null) {
                try {
                    total = total + Double.parseDouble(subTotal.toString());
                } catch (NumberFormatException ex) {
                }
            }
        }
        txtTotal.setText(String.valueOf(total));
    }

    public void agregarProductoDetalle(int id, String nombre, double precio) {
        modeloDetalle = (DefaultTableModel) tblDetalle.getModel();
        for (int fila = 0; fila < modeloDetalle.getRowCount(); fila++) {
            int idExistente;
            try {
                idExistente = Integer.parseInt(modeloDetalle.getValueAt(fila, 0).toString());
            } catch (NumberFormatException ex) {
                continue;
            }
            if (idExistente == id) {
                int cantidad;
                try {
                    cantidad = Integer.parseInt(modeloDetalle.getValueAt(fila, 3).toString());
                } catch (NumberFormatException ex) {
                    cantidad = 1;
                }
                cantidad = cantidad + 1;
                modeloDetalle.setValueAt(cantidad, fila, 3);
                double subtotal = precio * cantidad;
                modeloDetalle.setValueAt(subtotal, fila, 4);
                calcularTotal();
                return;
            }
        }
        int cantidad = 1;
        double subtotal = precio * cantidad;
        modeloDetalle.addRow(new Object[]{id, nombre, precio, cantidad, subtotal});
        calcularTotal();
    }

    public List<Detalle> obtenerDetalleDesdeTabla() {
        List<Detalle> lista = new ArrayList<>();
        for (int fila = 0; fila < tblDetalle.getRowCount(); fila++) {
            int idProducto;
            double precio;
            int cantidad;
            try {
                idProducto = Integer.parseInt(tblDetalle.getValueAt(fila, 0).toString());
                precio = Double.parseDouble(tblDetalle.getValueAt(fila, 2).toString());
                cantidad = Integer.parseInt(tblDetalle.getValueAt(fila, 3).toString());
            } catch (NumberFormatException ex) {
                continue;
            }
            Producto p = new Producto();
            p.setIdProducto(String.valueOf(idProducto));
            p.setPrecio(precio);
            Detalle d = new Detalle();
            d.setProducto(p);
            d.setCantidad(cantidad);
            lista.add(d);
        }
        return lista;
    }

    public void cargarTablaDetalles(int idCombo) {
        DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();
        modelo.setRowCount(0);
        DetalleDAO detalleDao = new DetalleDAO();
        List<Detalle> detalles = detalleDao.obtenerDetallesPorCombo(idCombo);
        for (Detalle d : detalles) {
            modelo.addRow(new Object[]{
                d.getProducto().getIdProducto(),
                d.getProducto().getNombre(),
                d.getProducto().getPrecio(),
                d.getCantidad(),
                d.getSubtotal()
            });
        }
        calcularTotal();
    }

    public void llenarTabla(List<Producto> lista) {
        modeloProducto = (DefaultTableModel) tblProductos.getModel();
        modeloProducto.setRowCount(0);
        for (Producto p : lista) {
            modeloProducto.addRow(new Object[]{
                p.getIdProducto(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getCategoria().getIdCategoria(),
                p.getCategoria().getCategoria()
            });
        }
    }

    private void llenarTabla() {
        modeloProducto = new DefaultTableModel();
        modeloProducto.addColumn("Id producto");
        modeloProducto.addColumn("Nombre");
        modeloProducto.addColumn("Descripción");
        modeloProducto.addColumn("Precio");
        modeloProducto.addColumn("Id categoría");
        modeloProducto.addColumn("Categoría");
        ProductoDAO dao = new ProductoDAO();
        for (Producto elemento : dao.listar()) {
            modeloProducto.addRow(new Object[]{
                elemento.getIdProducto(),
                elemento.getNombre(),
                elemento.getDescripcion(),
                elemento.getPrecio(),
                elemento.getCategoria().getIdCategoria(),
                elemento.getCategoria().getCategoria()
            });
        }
        tblProductos.setModel(modeloProducto);
        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblProductos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblProductos.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblProductos.getColumnModel().getColumn(4).setPreferredWidth(30);
        tblProductos.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblProductos.getTableHeader().setResizingAllowed(false);
        tblProductos.getTableHeader().setReorderingAllowed(false);
    }

    private void cargarComboBox() {
        ComboDAO comboDao = new ComboDAO();
        List<Combo> lista = comboDao.listarCombos();
        cmbCombo.removeAllItems();
        cmbCombo.addItem(null);
        for (Combo c : lista) {
            cmbCombo.addItem(c);
        }
    }

    private void crearDetalle() {
        modeloDetalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };
        modeloDetalle.addColumn("ID");
        modeloDetalle.addColumn("Nombre");
        modeloDetalle.addColumn("Precio");
        modeloDetalle.addColumn("Cantidad");
        modeloDetalle.addColumn("Subtotal");
        tblDetalle.setModel(modeloDetalle);
        tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblDetalle.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblDetalle.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        tblDetalle.getTableHeader().setResizingAllowed(false);
        tblDetalle.getTableHeader().setReorderingAllowed(false);

        javax.swing.JTextField txtCantidadEditor = new javax.swing.JTextField();
        txtCantidadEditor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar())) evt.consume();
            }
        });
        tblDetalle.getColumnModel().getColumn(3).setCellEditor(
            new javax.swing.DefaultCellEditor(txtCantidadEditor));

        modeloDetalle = (DefaultTableModel) tblDetalle.getModel();

        TableModelListener listener = new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() != TableModelEvent.UPDATE) return;
                int fila = e.getFirstRow();
                int columna = e.getColumn();
                if (columna != 3) return;
                Object valorCantidadObj = modeloDetalle.getValueAt(fila, 3);
                Object valorPrecioObj = modeloDetalle.getValueAt(fila, 2);
                if (valorCantidadObj == null || valorPrecioObj == null) return;
                try {
                    double cantidad = Double.parseDouble(valorCantidadObj.toString());
                    double precio = Double.parseDouble(valorPrecioObj.toString());
                    if (cantidad <= 0) {
                        cantidad = 1;
                        modeloDetalle.setValueAt(1, fila, 3);
                    }
                    double subTotal = precio * cantidad;
                    modeloDetalle.removeTableModelListener(this);
                    modeloDetalle.setValueAt(subTotal, fila, 4);
                    modeloDetalle.addTableModelListener(this);
                    calcularTotal();
                } catch (NumberFormatException ex) {
                    modeloDetalle.setValueAt(1, fila, 3);
                }
            }
        };
        modeloDetalle.addTableModelListener(listener);
        txtTotal.setText("0.0");

        tblDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
                    int fila = tblDetalle.getSelectedRow();
                    if (fila >= 0) {
                        modeloDetalle.removeRow(fila);
                        calcularTotal();
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
                    }
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarFila;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JComboBox cmbCombo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCombo;
    private javax.swing.JLabel lblCombos;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblIdCombo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlComboSelector;
    private javax.swing.JPanel pnlDetalleInferior;
    private javax.swing.JPanel pnlDetalleSeccion;
    private javax.swing.JPanel pnlProductos;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JPanel pnlTotales;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCombo;
    private javax.swing.JTextField txtIdCombo;
    private javax.swing.JTextField txtPrecioCombo;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
