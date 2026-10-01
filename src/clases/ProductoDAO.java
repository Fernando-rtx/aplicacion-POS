package clases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoDAO {

    public boolean insertar(Producto producto) {
        String sql = "INSERT INTO producto (idProducto, nombre, descripcion, precio, idCategoria) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setString(5, producto.getCategoria().getIdCategoria());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar Producto: " + e.getMessage());
            return false;
        }
    }

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT producto.*, categoria.idCategoria, categoria.categoria "
                   + "FROM producto INNER JOIN categoria ON producto.idCategoria = categoria.idCategoria";
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getString("idProducto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    new Categoria(
                        rs.getString("idCategoria"),
                        rs.getString("categoria")
                    )
                );
                lista.add(producto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar productos: " + e.getMessage());
        }
        return lista;
    }

    public List<Producto> buscarProductos(String texto) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT producto.*, categoria.categoria "
                   + "FROM producto "
                   + "INNER JOIN categoria ON producto.idCategoria = categoria.idCategoria "
                   + "WHERE nombre LIKE ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + texto + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getString("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getString("idCategoria"));
                c.setCategoria(rs.getString("categoria"));
                p.setCategoria(c);
                lista.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }

    public boolean eliminar(String idProducto) {
        String checkSql = "SELECT COUNT(*) FROM detalleorden WHERE idProducto = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement psCheck = con.prepareStatement(checkSql)) {
            psCheck.setString(1, idProducto);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new RuntimeException("El producto ya está asociado a una orden y no se puede eliminar.");
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

        int confirmar = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar este producto?", "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
        if (confirmar != JOptionPane.YES_OPTION) {
            return false;
        }

        String sql = "DELETE FROM producto WHERE idProducto=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idProducto);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean actualizarProducto(Producto p) {
        String sql = "UPDATE producto SET nombre=?, descripcion=?, precio=?, idCategoria=? WHERE idProducto=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setString(4, p.getCategoria().getIdCategoria());
            ps.setString(5, p.getIdProducto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public int obtenerUltimoId() {
        int id = 0;
        String sql = "SELECT MAX(idProducto) FROM producto";
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Producto> listarPorCategoria(int idCategoria) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT idProducto, nombre, precio FROM producto WHERE idCategoria = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(String.valueOf(rs.getInt("idProducto")));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                lista.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
}
