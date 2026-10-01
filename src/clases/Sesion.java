package clases;

import java.util.ArrayList;

public class Sesion {

    private static Usuario usuarioActual;
    private static ArrayList<String> permisos;

    public static void iniciarSesion(Usuario usuario, ArrayList<String> listaPermisos) {
        usuarioActual = usuario;
        permisos = listaPermisos;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static boolean tienePermiso(String permiso) {
        if (permisos == null) return false;
        return permisos.contains(permiso);
    }

    public static void cerrarSesion() {
        usuarioActual = null;
        permisos = null;
    }
}
