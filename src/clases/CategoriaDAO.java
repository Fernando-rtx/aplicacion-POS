package clases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaDAO {

    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Categoria categoria = new Categoria(
                    rs.getString("idCategoria"),
                    rs.getString("categoria")
                );
                lista.add(categoria);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar categoria: " + e.getMessage());
        }
        return lista;
    }
}
