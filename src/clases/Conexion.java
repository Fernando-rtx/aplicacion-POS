package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String URL = "jdbc:mariadb://localhost:3306/rinconazteca";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private static Connection conexion = null;

    public static Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: No se encontró el driver de MariaDB.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    public static void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
