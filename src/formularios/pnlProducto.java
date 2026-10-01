package formularios;

import clases.Categoria;
import clases.CategoriaDAO;
import clases.Producto;
import clases.ProductoDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class pnlProducto extends javax.swing.JPanel {

    public pnlProducto() {
        initComponents();
        ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/logoProductoFER.png"));
        Image imagen = icono.getImage();
        Image imagenEscalada = imagen.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(imagenEscalada));
        this.listarTabla();
        this.cargarCategorias();
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '.') evt.consume();
                if (c == '.' && txtPrecio.getText().contains(".")) evt.consume();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lblCategoria = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblIdProducto = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        lblDescripcion = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        txtDescripcion = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        txtPrecio = new javax.swing.JTextField();

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
            }
        ));
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCategoria.setText("Categoría");

        lblLogo.setVerifyInputWhenFocusTarget(false);

        lblIdProducto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblIdProducto.setText("Id de producto");

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBuscar.setText("Buscar por nombre");

        txtIdProducto.setEditable(false);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNombre.setText("Nombre");

        btnLimpiar.setBackground(new java.awt.Color(200, 230, 235));
        btnLimpiar.setForeground(new java.awt.Color(10, 50, 60));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnEliminar.setBackground(new java.awt.Color(200, 230, 235));
        btnEliminar.setForeground(new java.awt.Color(10, 50, 60));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnGuardar.setBackground(new java.awt.Color(0, 149, 183));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnActualizar.setBackground(new java.awt.Color(200, 230, 235));
        btnActualizar.setForeground(new java.awt.Color(10, 50, 60));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDescripcion.setText("Descripción");

        btnNuevo.setBackground(new java.awt.Color(0, 149, 183));
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPrecio.setText("Precio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNombre)
                                    .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPrecio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(btnNuevo))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btnLimpiar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnEliminar)
                                    .addGap(28, 28, 28)
                                    .addComponent(btnActualizar)
                                    .addGap(29, 29, 29)
                                    .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdProducto)
                            .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDescripcion)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrecio)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCategoria)
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar)
                        .addComponent(lblBuscar)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void llenarTabla(List<Producto> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.setRowCount(0);
        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                p.getIdProducto(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getCategoria().getIdCategoria(),
                p.getCategoria().getCategoria()
            });
        }
    }

    private void cargarCategorias() {
        CategoriaDAO dao = new CategoriaDAO();
        for (Categoria c : dao.listar()) {
            ((JComboBox) cmbCategoria).addItem(c);
        }
    }

    public void listarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id producto");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio");
        modelo.addColumn("Id categoría");
        modelo.addColumn("Categoría");

        ProductoDAO dao = new ProductoDAO();
        for (Producto elemento : dao.listar()) {
            modelo.addRow(new Object[]{
                elemento.getIdProducto(),
                elemento.getNombre(),
                elemento.getDescripcion(),
                elemento.getPrecio(),
                elemento.getCategoria().getIdCategoria(),
                elemento.getCategoria().getCategoria()
            });
        }

        tblProductos.setModel(modelo);
        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblProductos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(500);
        tblProductos.getColumnModel().getColumn(3).setPreferredWidth(10);
        tblProductos.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblProductos.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblProductos.getTableHeader().setResizingAllowed(false);
        tblProductos.getTableHeader().setReorderingAllowed(false);
    }

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        int fila = tblProductos.getSelectedRow();
        txtIdProducto.setText(tblProductos.getValueAt(fila, 0).toString());
        txtNombre.setText(tblProductos.getValueAt(fila, 1).toString());
        txtDescripcion.setText(tblProductos.getValueAt(fila, 2).toString());
        txtPrecio.setText(tblProductos.getValueAt(fila, 3).toString());
        String categoriaTabla = tblProductos.getValueAt(fila, 5).toString();
        for (int i = 0; i < cmbCategoria.getItemCount(); i++) {
            Object item = cmbCategoria.getItemAt(i);
            Categoria cat = (Categoria) item;
            if (cat.getCategoria().equals(categoriaTabla)) {
                cmbCategoria.setSelectedIndex(i);
                break;
            }
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void limpiar() {
        txtIdProducto.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtBuscar.setText("");
        cmbCategoria.setSelectedIndex(0);
        tblProductos.clearSelection();
    }

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String texto = txtBuscar.getText();
        ProductoDAO dao = new ProductoDAO();
        List<Producto> lista;
        if (texto.isEmpty()) {
            lista = dao.listar();
        } else {
            lista = dao.buscarProductos(texto);
        }
        llenarTabla(lista);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
        listarTabla();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
            return;
        }
        try {
            int idProducto = Integer.parseInt(tblProductos.getValueAt(fila, 0).toString());
            ProductoDAO dao = new ProductoDAO();
            if (dao.eliminar(String.valueOf(idProducto))) {
                JOptionPane.showMessageDialog(null, "Producto eliminado");
                listarTabla();
                limpiar();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        CategoriaDAO dao = new CategoriaDAO();
        ProductoDAO dao1 = new ProductoDAO();
        String id, nombre, descripcion, precio;
        id = txtIdProducto.getText();
        nombre = txtNombre.getText();
        descripcion = txtDescripcion.getText();
        precio = txtPrecio.getText();
        if (id.trim().equals("")) { txtIdProducto.requestFocus(); return; }
        if (nombre.trim().equals("")) { txtNombre.requestFocus(); return; }
        if (descripcion.trim().equals("")) { txtDescripcion.requestFocus(); return; }
        if (precio.trim().equals("")) { txtPrecio.requestFocus(); return; }

        try {
            if (Integer.parseInt(id) > 0) {
                if (Double.parseDouble(precio) > 0.0) {
                    if (cmbCategoria.getSelectedIndex() < 0) {
                        JOptionPane.showMessageDialog(null, "Seleccione una categoría");
                        return;
                    }
                    List<Categoria> lista = dao.listar();
                    Producto producto = new Producto();
                    Categoria cat;
                    producto.setIdProducto(id);
                    producto.setNombre(nombre);
                    producto.setDescripcion(descripcion);
                    producto.setPrecio(Double.parseDouble(precio));
                    int idcategoria = cmbCategoria.getSelectedIndex();
                    cat = lista.get(idcategoria);
                    producto.setCategoria(cat);
                    dao1.insertar(producto);
                    JOptionPane.showMessageDialog(null, "Guardado con éxito");
                    this.listarTabla();
                    this.limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Precio incorrecto");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Id fuera de rango");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        String nombre, descripcion, precio;
        nombre = txtNombre.getText();
        descripcion = txtDescripcion.getText();
        precio = txtPrecio.getText();
        if (nombre.trim().equals("")) { txtNombre.requestFocus(); return; }
        if (descripcion.trim().equals("")) { txtDescripcion.requestFocus(); return; }
        if (precio.trim().equals("")) { txtPrecio.requestFocus(); return; }

        try {
            if (Double.parseDouble(precio) > 0.0) {
                if (cmbCategoria.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Seleccione una categoría");
                    return;
                }
                Producto p = new Producto();
                p.setIdProducto(txtIdProducto.getText());
                p.setNombre(txtNombre.getText());
                p.setDescripcion(txtDescripcion.getText());
                p.setPrecio(Double.parseDouble(txtPrecio.getText()));
                Categoria c = (Categoria) cmbCategoria.getSelectedItem();
                p.setCategoria(c);
                ProductoDAO dao = new ProductoDAO();
                if (dao.actualizarProducto(p)) {
                    JOptionPane.showMessageDialog(this, "Producto actualizado");
                    limpiar();
                    listarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Precio incorrecto");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        int id = 0;
        ProductoDAO dao = new ProductoDAO();
        id = dao.obtenerUltimoId();
        txtIdProducto.setText(String.valueOf(id + 1));
    }//GEN-LAST:event_btnNuevoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
