package clases;

public class DetalleOrden {

    private String tipo;
    private int id;
    private int cantidad;
    private double precio;
    private int idLinea;
    private String nombre;

    public DetalleOrden(String tipo, int id, int cantidad, double precio) {
        this.tipo = tipo;
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleOrden() {
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getIdLinea() { return idLinea; }
    public void setIdLinea(int idLinea) { this.idLinea = idLinea; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSubTotal() { return this.precio * this.cantidad; }
}
