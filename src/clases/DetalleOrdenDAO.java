package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleOrdenDAO {

    private int obtenerSiguienteLinea(Connection con, int idOrden) throws SQLException {
        String sql = "SELECT COALESCE(MAX(idLinea), 0) + 1 FROM detalleorden WHERE idOrden = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idOrden);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt(1);
        return 1;
    }

    public void insertarDetalle(Connection con, int idOrden, DetalleOrden d) throws SQLException {
        int idLinea = obtenerSiguienteLinea(con, idOrden);
        String sql;
        if (d.getTipo().equals("PRODUCTO")) {
            sql = "INSERT INTO detalleorden (idOrden, idLinea, idProducto, cantidad, precioUnitario) VALUES (?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO detalleorden (idOrden, idLinea, idCombo, cantidad, precioUnitario) VALUES (?, ?, ?, ?, ?)";
        }
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idOrden);
        ps.setInt(2, idLinea);
        ps.setInt(3, d.getId());
        ps.setInt(4, d.getCantidad());
        ps.setDouble(5, d.getPrecio());
        ps.executeUpdate();
    }

    public List<DetalleOrden> listarPorOrden(int idOrden) throws SQLException {
        List<DetalleOrden> lista = new ArrayList<>();
        String sql =
            "SELECT do.idLinea, 'PRODUCTO' AS tipo, do.idProducto AS idItem, " +
            "       p.nombre, do.precioUnitario, do.cantidad " +
            "FROM detalleorden do " +
            "JOIN producto p ON do.idProducto = p.idProducto " +
            "WHERE do.idOrden = ? AND do.idProducto IS NOT NULL " +
            "UNION " +
            "SELECT do.idLinea, 'COMBO' AS tipo, do.idCombo AS idItem, " +
            "       c.combo AS nombre, do.precioUnitario, do.cantidad " +
            "FROM detalleorden do " +
            "JOIN combo c ON do.idCombo = c.idCombo " +
            "WHERE do.idOrden = ? AND do.idCombo IS NOT NULL " +
            "ORDER BY idLinea";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idOrden);
            ps.setInt(2, idOrden);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DetalleOrden d = new DetalleOrden(
                    rs.getString("tipo"),
                    rs.getInt("idItem"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precioUnitario")
                );
                d.setIdLinea(rs.getInt("idLinea"));
                d.setNombre(rs.getString("nombre"));
                lista.add(d);
            }
        }
        return lista;
    }
}
