package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PermisoDAO {

    public ArrayList<String> obtenerPermisosPorRol(int idRol) {
        ArrayList<String> permisos = new ArrayList<>();
        String sql = """
            SELECT p.permiso
            FROM permiso p
            INNER JOIN rolpermiso rp
                ON p.idPermiso = rp.idPermiso
            WHERE rp.idRol = ?
        """;
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                permisos.add(rs.getString("permiso"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return permisos;
    }
}
