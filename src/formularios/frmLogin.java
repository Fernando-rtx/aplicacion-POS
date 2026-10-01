package formularios;

import clases.PermisoDAO;
import clases.Sesion;
import clases.Usuario;
import clases.UsuarioDAO;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class frmLogin extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmLogin.class.getName());

    // Paleta de colores Azul/Verde (Cool Ocean)
    private static final Color BG_PRINCIPAL    = new Color(234, 244, 248); // #EAF4F8 - Azul hielo claro
    private static final Color BG_PANEL        = new Color(255, 255, 255); // #FFFFFF - Blanco
    private static final Color BTN_PRIMARIO    = new Color(0, 149, 183);   // #0095B7 - Turquesa vibrante
    private static final Color BTN_PRIMARIO_HOVER = new Color(0, 122, 142); // #007A8E - Turquesa oscuro
    private static final Color BTN_SECUNDARIO  = new Color(200, 230, 235); // #C8E6EB - Azul muy claro
    private static final Color BTN_SECUNDARIO_HOVER = new Color(170, 210, 220); // Hover azul claro
    private static final Color TEXTO_OSCURO    = new Color(10, 50, 60);    // #0A323C - Azul marino
    private static final Color TEXTO_CLARO     = new Color(255, 255, 255); // Blanco
    private static final Color BORDE           = new Color(0, 149, 183);   // #0095B7 - Turquesa
    private static final Color SIDEBAR_BG      = new Color(0, 79, 100);    // #004F64 - Azul profundo

    public frmLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/login.png"));
        Image imagen = icono.getImage();
        Image imagenEscalada = imagen.getScaledInstance(200, 170, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(imagenEscalada));
        pswContrasenia.setText("");

        this.getContentPane().setBackground(BG_PRINCIPAL);

        // Estilo botones
        btnAceptar.setBackground(BTN_PRIMARIO);
        btnAceptar.setForeground(TEXTO_CLARO);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorderPainted(false);
        btnAceptar.setOpaque(true);

        btnLimpiar.setBackground(BTN_SECUNDARIO);
        btnLimpiar.setForeground(TEXTO_OSCURO);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setOpaque(true);

        btnCerrar.setBackground(BTN_SECUNDARIO);
        btnCerrar.setForeground(TEXTO_OSCURO);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setOpaque(true);

        // Bordes redondeados a campos
        txtUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(BORDE, 1, true),
            javax.swing.BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        pswContrasenia.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(BORDE, 1, true),
            javax.swing.BorderFactory.createEmptyBorder(4, 8, 4, 8)));

        lblUsuario.setForeground(TEXTO_OSCURO);
        lblContrasenia.setForeground(TEXTO_OSCURO);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblContrasenia = new javax.swing.JLabel();
        pswContrasenia = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenido - El Rincón Azteca");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsuario.setText("Usuario");

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        lblContrasenia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblContrasenia.setText("Contraseña");

        pswContrasenia.setText("jPasswordField1");
        pswContrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pswContraseniaKeyTyped(evt);
            }
        });

        btnAceptar.setBackground(new java.awt.Color(255, 204, 204));
        btnAceptar.setForeground(new java.awt.Color(0, 0, 0));
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/login.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);

        btnLimpiar.setBackground(new java.awt.Color(200, 230, 235));
        btnLimpiar.setForeground(new java.awt.Color(10, 50, 60));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnCerrar.setBackground(new java.awt.Color(200, 230, 235));
        btnCerrar.setForeground(new java.awt.Color(10, 50, 60));
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario)
                            .addComponent(pswContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasenia)
                    .addComponent(pswContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnCerrar))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        String user = txtUsuario.getText();
        String pass = new String(pswContrasenia.getPassword());

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.login(user, pass);

        if (usuario != null) {
            PermisoDAO permisoDAO = new PermisoDAO();
            ArrayList<String> permisos = permisoDAO.obtenerPermisosPorRol(usuario.getIdRol());
            Sesion.iniciarSesion(usuario, permisos);

            JOptionPane.showMessageDialog(this, "Bienvenido " + usuario);

            new frmPrincipal().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            btnLimpiar.doClick();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtUsuario.setText("");
        pswContrasenia.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void pswContraseniaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pswContraseniaKeyTyped
    }//GEN-LAST:event_pswContraseniaKeyTyped

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
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
        java.awt.EventQueue.invokeLater(() -> new frmLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField pswContrasenia;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
