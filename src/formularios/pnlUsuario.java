package formularios;

import clases.Sesion;
import clases.Usuario;
import clases.UsuarioDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class pnlUsuario extends javax.swing.JPanel {

    private UsuarioDAO dao = new UsuarioDAO();
    private int idSeleccionado = -1;

    public pnlUsuario() {
        initComponents();
        configurarTabla();
        cargarMeseros();

        tblUsuario.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarSeleccion();
            }
        });
    }

    private void configurarTabla() {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new String[]{"ID", "Nombre", "Apellido", "Usuario", "Rol"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblUsuario.setModel(modelo);
    }

    private void cargarMeseros() {
        javax.swing.table.DefaultTableModel modelo
                = (javax.swing.table.DefaultTableModel) tblUsuario.getModel();
        modelo.setRowCount(0);
        List<Usuario> usuarios;
        if ("PROGRAMADOR".equals(Sesion.getUsuarioActual().getRol())) {
            usuarios = dao.listarTodos();
        } else {
            usuarios = dao.listarMeseros();
        }
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{
                u.getIdUsuario(), u.getNombre(),
                u.getApellido(), u.getUsuario(), u.getRol()
            });
        }
    }

    private void cargarSeleccion() {
        int fila = tblUsuario.getSelectedRow();
        if (fila < 0) return;
        javax.swing.table.DefaultTableModel m =
            (javax.swing.table.DefaultTableModel) tblUsuario.getModel();
        Object val = m.getValueAt(fila, 0);
        idSeleccionado = (val instanceof Number) ? ((Number) val).intValue() : -1;
        txtNombre.setText(m.getValueAt(fila, 1).toString());
        txtApellido.setText(m.getValueAt(fila, 2).toString());
        txtUsuario.setText(m.getValueAt(fila, 3).toString());
        txtContrasenia.setText("");
        txtContrasenia.setEnabled(false);
        txtContrasenia.setToolTipText("Presiona Limpiar si deseas cambiar la contraseña");
    }

    private void limpiar() {
        idSeleccionado = -1;
        txtNombre.setText("");
        txtApellido.setText("");
        txtUsuario.setText("");
        txtContrasenia.setText("");
        txtContrasenia.setEnabled(true);
        txtContrasenia.setToolTipText("");
        tblUsuario.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblContrasenia = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtContrasenia = new javax.swing.JPasswordField();

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {
            }
        ));
        jScrollPane1.setViewportView(tblUsuario);

        lblNombre.setText("Nombre");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        lblApellido.setText("Apellido");

        lblUsuario.setText("Usuario");

        lblContrasenia.setText("Contraseña");

        btnEliminar.setBackground(new java.awt.Color(200, 230, 235));
        btnEliminar.setForeground(new java.awt.Color(10, 50, 60));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnInsertar.setBackground(new java.awt.Color(0, 149, 183));
        btnInsertar.setForeground(new java.awt.Color(255, 255, 255));
        btnInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(this::btnInsertarActionPerformed);

        btnActualizar.setBackground(new java.awt.Color(200, 230, 235));
        btnActualizar.setForeground(new java.awt.Color(10, 50, 60));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnLimpiar.setBackground(new java.awt.Color(200, 230, 235));
        btnLimpiar.setForeground(new java.awt.Color(10, 50, 60));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        txtContrasenia.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblUsuario)
                            .addComponent(lblApellido)
                            .addComponent(lblContrasenia))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUsuario)
                                .addComponent(txtNombre)
                                .addComponent(txtContrasenia, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(btnInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnInsertar)
                        .addGap(10, 10, 10)
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(12, 12, 12)
                        .addComponent(btnLimpiar)
                        .addGap(38, 38, 38))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblApellido)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblContrasenia)
                            .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {
        String pass = new String(txtContrasenia.getPassword());
        if (txtNombre.getText().isBlank() || txtApellido.getText().isBlank()
                || txtUsuario.getText().isBlank() || pass.isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }
        try {
            boolean ok = dao.insertar(
                    txtNombre.getText().trim(),
                    txtApellido.getText().trim(),
                    txtUsuario.getText().trim(),
                    pass
            );
            if (ok) {
                JOptionPane.showMessageDialog(this, "Mesero agregado correctamente.");
                limpiar();
                cargarMeseros();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un mesero de la tabla.");
            return;
        }
        String pass = new String(txtContrasenia.getPassword()).trim();
        try {
            boolean ok = dao.actualizar(
                    idSeleccionado,
                    txtNombre.getText().trim(),
                    txtApellido.getText().trim(),
                    txtUsuario.getText().trim(),
                    pass.isEmpty() ? null : pass
            );
            if (ok) {
                JOptionPane.showMessageDialog(this, "Mesero actualizado.");
                limpiar();
                cargarMeseros();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un mesero de la tabla.");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Eliminar al mesero seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmar != JOptionPane.YES_OPTION) return;
        try {
            if (dao.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Mesero eliminado.");
                limpiar();
                cargarMeseros();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiar();
    }

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
            getToolkit().beep();
        }
    }

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
            getToolkit().beep();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
