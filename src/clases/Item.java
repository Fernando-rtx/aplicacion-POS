package clases;

public class Item {

    private int id;
    private String nombre;
    private double precio;
    private String tipo;

    public Item(int id, String nombre, double precio, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getTipo() { return tipo; }

    @Override
    public String toString() {
        return nombre + " ($" + String.format("%.2f", precio) + ")";
    }
}
