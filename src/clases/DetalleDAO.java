package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DetalleDAO {

    public void guardarDetalle(int idCombo, List<Detalle> listaDetalle) {
        String sql = "INSERT INTO detalle (idCombo, idProducto, cantidad) VALUES (?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (Detalle elemento : listaDetalle) {
                ps.setInt(1, idCombo);
                ps.setString(2, elemento.getProducto().getIdProducto());
                ps.setInt(3, elemento.getCantidad());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public List<Detalle> obtenerDetallesPorCombo(int idCombo) {
        List<Detalle> listaDetalles = new ArrayList<>();
        String sql = "SELECT d.idProducto, p.nombre, p.precio, d.cantidad "
                   + "FROM detalle d "
                   + "INNER JOIN producto p ON d.idProducto = p.idProducto "
                   + "WHERE d.idCombo = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCombo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getString("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));

                Detalle d = new Detalle();
                d.setProducto(p);
                d.setCantidad(rs.getInt("cantidad"));
                listaDetalles.add(d);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener detalles por combo: " + e.getMessage());
        }
        return listaDetalles;
    }

    public boolean eliminarPorCombo(int idCombo) {
        String sql = "DELETE FROM detalle WHERE idCombo = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCombo);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar detalles: " + e.getMessage());
            return false;
        }
    }
}
