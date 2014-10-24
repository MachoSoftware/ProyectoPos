package machosoftware.proyectopos;

/**
 * Created by NewOldDeilan on 24-10-2014.
 */
public class VentaItem {
    private String nombre;
    private int cantidad;
    private int precio;
    private float descuento;

    public VentaItem(String nombre, int cantidad, int precio, float descuento) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float valor) {
        descuento = valor;
    }
}
