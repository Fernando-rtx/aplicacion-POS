package clases;

import java.sql.Connection;
import java.util.List;

public class OrdenService {

    private final DetalleOrdenDAO detalleDAO = new DetalleOrdenDAO();
    private final OrdenDAO ordenDAO = new OrdenDAO();
    private final TicketPDF ticketPDF = new TicketPDF();

    public int procesarOrden(List<DetalleOrden> detalle) {
        Connection con = null;
        try {
            con = Conexion.conectar();
            con.setAutoCommit(false);

            double total = 0;
            for (DetalleOrden d : detalle) total += d.getCantidad() * d.getPrecio();

            int idUsuario = Sesion.getUsuarioActual().getIdUsuario();
            int idOrden = ordenDAO.insertarOrden(con, total, idUsuario);

            for (DetalleOrden d : detalle) detalleDAO.insertarDetalle(con, idOrden, d);

            con.commit();
            return idOrden;
        } catch (Exception e) {
            try { if (con != null) con.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            throw new RuntimeException(e);
        } finally {
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public Orden buscarOrden(int idOrden) {
        try {
            Orden orden = ordenDAO.buscarPorId(idOrden);
            if (orden == null) return null;
            List<DetalleOrden> detalles = detalleDAO.listarPorOrden(idOrden);
            for (DetalleOrden d : detalles) orden.agregarDetalle(d);
            return orden;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void despacharOrden(int idOrden) {
        if (!Sesion.tienePermiso("ACCESO_ORDEN")) {
            throw new RuntimeException("No tiene permiso para despachar órdenes.");
        }
        try {
            Orden orden = ordenDAO.buscarPorId(idOrden);
            if (orden == null) throw new RuntimeException("Orden no encontrada.");
            if (!"PROCESADA".equalsIgnoreCase(orden.getEstado())) {
                throw new RuntimeException("Solo se pueden despachar órdenes en estado PROCESADA.");
            }
            ordenDAO.cambiarEstado(idOrden, "DESPACHADA");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void anularOrden(int idOrden) {
        if (!Sesion.tienePermiso("ANULAR_ORDEN")) {
            throw new RuntimeException("Solo el Administrador puede anular órdenes.");
        }
        try {
            Orden orden = ordenDAO.buscarPorId(idOrden);
            if (orden == null) throw new RuntimeException("Orden no encontrada.");
            if ("ANULADA".equalsIgnoreCase(orden.getEstado())) {
                throw new RuntimeException("La orden ya está anulada.");
            }
            if ("DESPACHADA".equalsIgnoreCase(orden.getEstado())) {
                throw new RuntimeException("No se puede anular una orden que ya fue despachada");
            }
            ordenDAO.cambiarEstado(idOrden, "ANULADA");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
