package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrdenDAO {

    public int insertarOrden(Connection con, double total, int idUsuario) throws SQLException {
        String sql = "INSERT INTO orden (total, idUsuario, estado) VALUES (?, ?, 'PROCESADA')";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setDouble(1, total);
        ps.setInt(2, idUsuario);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);
        throw new SQLException("No se pudo obtener el ID de la orden");
    }

    public Orden buscarPorId(int idOrden) throws SQLException {
        String sql = "SELECT idOrden, fechaHora, idUsuario, total, estado "
                   + "FROM orden WHERE idOrden = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idOrden);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Orden(
                    rs.getInt("idOrden"),
                    rs.getString("fechaHora"),
                    rs.getInt("idUsuario"),
                    rs.getDouble("total"),
                    rs.getString("estado")
                );
            }
        }
        return null;
    }

    public void cambiarEstado(int idOrden, String nuevoEstado) throws SQLException {
        String sql = "UPDATE orden SET estado = ? WHERE idOrden = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idOrden);
            ps.executeUpdate();
        }
    }

    public java.util.List<Orden> listarRecientes(int limite) throws SQLException {
        java.util.List<Orden> lista = new java.util.ArrayList<>();
        String sql = "SELECT o.idOrden, o.fechaHora, o.total, o.estado, "
                   + "COALESCE(u.nombre, 'Eliminado') AS nombre "
                   + "FROM orden o LEFT JOIN usuario u ON o.idUsuario = u.idUsuario "
                   + "ORDER BY o.idOrden DESC LIMIT ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limite);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orden o = new Orden();
                o.setIdOrden(rs.getInt("idOrden"));
                o.setFechaHora(rs.getString("fechaHora"));
                o.setTotal(rs.getDouble("total"));
                o.setEstado(rs.getString("estado"));
                o.setUsuarioNombre(rs.getString("nombre"));
                lista.add(o);
            }
        }
        return lista;
    }
}
