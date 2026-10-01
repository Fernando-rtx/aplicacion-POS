package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    public Usuario login(String user, String password) {
        String sql = """
            SELECT u.idUsuario, u.nombre, u.apellido, u.usuario,
                   u.idRol, r.rol
            FROM usuario u
            INNER JOIN rol r ON u.idRol = r.idRol
            WHERE u.usuario = ?
            AND u.contrasenia = SHA2(?, 256)
        """;
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setUsuario(rs.getString("usuario"));
                u.setIdRol(rs.getInt("idRol"));
                u.setRol(rs.getString("rol"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> listarMeseros() {
        List<Usuario> lista = new ArrayList<>();
        String sql = """
            SELECT u.idUsuario, u.nombre, u.apellido, u.usuario, u.idRol, r.rol
            FROM usuario u
            INNER JOIN rol r ON u.idRol = r.idRol
            WHERE r.rol = 'MESERO'
            ORDER BY u.nombre
        """;
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("idUsuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("usuario"),
                    rs.getInt("idRol"),
                    rs.getString("rol")
                );
                lista.add(u);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar meseros: " + e.getMessage());
        }
        return lista;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = """
            SELECT u.idUsuario, u.nombre, u.apellido, u.usuario, u.idRol, r.rol
            FROM usuario u
            INNER JOIN rol r ON u.idRol = r.idRol
            ORDER BY u.nombre
        """;
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("idUsuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("usuario"),
                    rs.getInt("idRol"),
                    rs.getString("rol")
                );
                lista.add(u);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(String nombre, String apellido, String usuario, String contrasenia) {
        if (existeUsuario(usuario, -1)) {
            throw new RuntimeException("Ya existe un usuario con ese nombre");
        }
        int idRolMesero = obtenerIdRolMesero();
        if (idRolMesero == -1) {
            JOptionPane.showMessageDialog(null, "No se encontró el rol MESERO en la base de datos.");
            return false;
        }
        String sql = """
            INSERT INTO usuario (nombre, apellido, usuario, contrasenia, idRol)
            VALUES (?, ?, ?, SHA2(?, 256), ?)
        """;
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, usuario);
            ps.setString(4, contrasenia);
            ps.setInt(5, idRolMesero);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar mesero: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(int idUsuario, String nombre, String apellido,
                              String usuario, String contrasenia) {
        if (existeUsuario(usuario, idUsuario)) {
            throw new RuntimeException("Ya existe un usuario con ese nombre");
        }
        String sql;
        if (contrasenia != null && !contrasenia.isBlank()) {
            sql = """
                UPDATE usuario
                SET nombre = ?, apellido = ?, usuario = ?, contrasenia = SHA2(?, 256)
                WHERE idUsuario = ?
            """;
        } else {
            sql = """
                UPDATE usuario
                SET nombre = ?, apellido = ?, usuario = ?
                WHERE idUsuario = ?
            """;
        }
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, usuario);
            if (contrasenia != null && !contrasenia.isBlank()) {
                ps.setString(4, contrasenia);
                ps.setInt(5, idUsuario);
            } else {
                ps.setInt(4, idUsuario);
            }
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar mesero: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idUsuario) throws SQLException {
        Connection con = null;
        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            String checkSql = "SELECT COUNT(*) FROM orden WHERE idUsuario = ? AND estado = 'PROCESADA'";
            try (PreparedStatement ps = con.prepareStatement(checkSql)) {
                ps.setInt(1, idUsuario);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new RuntimeException(
                        "No se puede eliminar: el mesero tiene ordenes en estado PROCESADA.");
                }
            }

            String delUsuario = "DELETE FROM usuario WHERE idUsuario = ?";
            try (PreparedStatement ps = con.prepareStatement(delUsuario)) {
                ps.setInt(1, idUsuario);
                ps.executeUpdate();
            }

            con.commit();
            return true;
        } catch (RuntimeException e) {
            if (con != null) try { con.rollback(); } catch (SQLException ex) {}
            throw e;
        } catch (Exception e) {
            if (con != null) try { con.rollback(); } catch (SQLException ex) {}
            throw new RuntimeException("Error al eliminar mesero: " + e.getMessage());
        } finally {
            if (con != null) try { con.close(); } catch (SQLException ex) {}
        }
    }

    public boolean existeUsuario(String usuario, int idExcluir) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE usuario = ? AND idUsuario <> ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setInt(2, idExcluir);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private int obtenerIdRolMesero() {
        String sql = "SELECT idRol FROM rol WHERE rol = 'MESERO' LIMIT 1";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt("idRol");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
