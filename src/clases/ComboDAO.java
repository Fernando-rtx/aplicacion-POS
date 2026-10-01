package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ComboDAO {

    public int obtenerIdCombo() {
        int id = 0;
        String sql = "SELECT MAX(idCombo) FROM combo";
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

    public int guardarCombo(int idCombo, String combo, double precioCombo) {
        String sql = "INSERT INTO combo (idCombo, combo, precioCombo) VALUES (?, ?, ?)";
        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCombo);
            ps.setString(2, combo);
            ps.setDouble(3, precioCombo);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
        return idCombo;
    }

    public boolean eliminar(int idCombo) {
        String checkSql = "SELECT COUNT(*) FROM detalleorden WHERE idCombo = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement psCheck = con.prepareStatement(checkSql)) {
            psCheck.setInt(1, idCombo);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new RuntimeException("El combo ya está asociado a una orden y no se puede eliminar.");
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

        int confirmar = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar este combo?", "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
        if (confirmar != JOptionPane.YES_OPTION) {
            return false;
        }

        String sqlDetalle = "DELETE FROM detalle WHERE idCombo=?";
        String sqlCombo = "DELETE FROM combo WHERE idCombo=?";
        Connection con = null;
        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(sqlDetalle);
            ps.setInt(1, idCombo);
            ps.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(sqlCombo);
            ps2.setInt(1, idCombo);
            int filas = ps2.executeUpdate();

            con.commit();
            return filas > 0;
        } catch (SQLException e) {
            if (con != null) try { con.rollback(); } catch (SQLException ex) {}
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
            return false;
        } finally {
            if (con != null) try { con.close(); } catch (SQLException ex) {}
        }
    }

    public boolean actualizarCombo(Combo c) {
        String sql = "UPDATE combo SET combo=?, precioCombo=? WHERE idCombo=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getCombo());
            ps.setDouble(2, c.getPrecio());
            ps.setInt(3, c.getIdCombo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public List<Combo> listarCombos() {
        List<Combo> listaCombos = new ArrayList<>();
        String sql = "SELECT idCombo, combo, precioCombo FROM combo";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Combo c = new Combo();
                c.setIdCombo(rs.getInt("idCombo"));
                c.setCombo(rs.getString("combo"));
                c.setPrecio(rs.getDouble("precioCombo"));
                listaCombos.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los combos: " + e.getMessage());
        }
        return listaCombos;
    }
}
