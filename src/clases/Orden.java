package clases;

import java.util.ArrayList;
import java.util.List;

public class Orden {

    private int idOrden;
    private String fechaHora;
    private int idUsuario;
    private double total;
    private String estado;
    private String usuarioNombre;
    private List<DetalleOrden> detalles = new ArrayList<>();

    public Orden() {
    }

    public Orden(int idOrden, String fechaHora, int idUsuario, double total, String estado) {
        this.idOrden = idOrden;
        this.fechaHora = fechaHora;
        this.idUsuario = idUsuario;
        this.total = total;
        this.estado = estado;
    }

    public int getIdOrden() { return idOrden; }
    public void setIdOrden(int v) { idOrden = v; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String v) { fechaHora = v; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int v) { idUsuario = v; }

    public double getTotal() { return total; }
    public void setTotal(double v) { total = v; }

    public String getEstado() { return estado; }
    public void setEstado(String v) { estado = v; }
    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String v) { usuarioNombre = v; }

    public List<DetalleOrden> getDetalles() { return detalles; }
    public void agregarDetalle(DetalleOrden d) { detalles.add(d); }
}
