package formularios;

import clases.Sesion;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class frmPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmPrincipal.class.getName());

    public frmPrincipal() {
        initComponents();
        pnlContenido.setLayout(new java.awt.BorderLayout());
        pnlBienvenida panel = new pnlBienvenida();
        mostrarPanel(panel);
        aplicarPermisos();
        txtUsuario.setText(Sesion.getUsuarioActual().toString());
        txtUsuario.setEditable(false);
        this.setLocationRelativeTo(null);
        Color azulClaro = new Color(234, 244, 248);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        pnlContenido.setBackground(azulClaro);
        pnlMenu.setBackground(azulClaro);
        this.getContentPane().setBackground(azulClaro);

        SwingUtilities.invokeLater(() -> {
            pnlContenido.revalidate();
            pnlContenido.repaint();
        });
    }

    private void aplicarPermisos() {
        btnProducto.setVisible(Sesion.tienePermiso("ACCESO_PRODUCTO"));
        btnCombo.setVisible(Sesion.tienePermiso("ACCESO_COMBO"));
        btnOrden.setVisible(Sesion.tienePermiso("ACCESO_ORDEN"));
        btnUsuario.setVisible(Sesion.tienePermiso("ACCESO_USUARIO"));
        SwingUtilities.invokeLater(() -> {
            pnlMenu.revalidate();
            pnlMenu.repaint();
        });
    }

    private void mostrarPanel(JPanel panel) {
        Color azulClaro = new Color(234, 244, 248);
        panel.setBackground(azulClaro);

        pnlContenido.removeAll();
        pnlContenido.add(panel, java.awt.BorderLayout.CENTER);

        pnlContenido.revalidate();
        pnlContenido.repaint();

        SwingUtilities.invokeLater(() -> {
            panel.revalidate();
            panel.repaint();
        });
    }

    private void mostrarPanelSeguro(JPanel panel, String permiso) {
        if (!Sesion.tienePermiso(permiso)) {
            JOptionPane.showMessageDialog(this, "No tiene permisos para acceder.");
            return;
        }
        mostrarPanel(panel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnCombo = new javax.swing.JButton();
        btnProducto = new javax.swing.JButton();
        btnOrden = new javax.swing.JButton();
        btnUsuario = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        lblusuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        pnlContenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenido a Rincón Azteca, una verdadera experiencia en comida Mexicana");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        btnCombo.setBackground(new java.awt.Color(0, 149, 183));
        btnCombo.setForeground(new java.awt.Color(255, 255, 255));
        btnCombo.setText("Combo");
        btnCombo.addActionListener(this::btnComboActionPerformed);

        btnProducto.setBackground(new java.awt.Color(0, 149, 183));
        btnProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnProducto.setText("Producto");
        btnProducto.addActionListener(this::btnProductoActionPerformed);

        btnOrden.setBackground(new java.awt.Color(0, 149, 183));
        btnOrden.setForeground(new java.awt.Color(255, 255, 255));
        btnOrden.setText("Orden");
        btnOrden.addActionListener(this::btnOrdenActionPerformed);

        btnUsuario.setBackground(new java.awt.Color(0, 149, 183));
        btnUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnUsuario.setText("Usuario");
        btnUsuario.addActionListener(this::btnUsuarioActionPerformed);

        btnCerrar.setBackground(new java.awt.Color(200, 230, 235));
        btnCerrar.setForeground(new java.awt.Color(10, 50, 60));
        btnCerrar.setText("Cerrar Sesión");
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);

        lblusuario.setText("Usuario");

        txtUsuario.setEditable(false);
        txtUsuario.setEnabled(false);

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblusuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(txtUsuario)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
                .addComponent(lblusuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1224, Short.MAX_VALUE)
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        mostrarPanelSeguro(new pnlProducto(), "ACCESO_PRODUCTO");
    }//GEN-LAST:event_btnProductoActionPerformed

    private void btnComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComboActionPerformed
        mostrarPanelSeguro(new pnlCombo(), "ACCESO_COMBO");
    }//GEN-LAST:event_btnComboActionPerformed

    private void btnOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenActionPerformed
        mostrarPanelSeguro(new pnlOrden(), "ACCESO_ORDEN");
    }//GEN-LAST:event_btnOrdenActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        mostrarPanelSeguro(new pnlUsuario(), "ACCESO_USUARIO");
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        int ok = JOptionPane.showConfirmDialog(this,
                "¿Desea cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            Sesion.cerrarSesion();
            new frmLogin().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnCerrarActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new frmPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCombo;
    private javax.swing.JButton btnOrden;
    private javax.swing.JButton btnProducto;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
