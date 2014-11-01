package machosoftware.proyectopos;

/**
 * Comentario asdasdsadadasdas
 * Created by Sebastian on 30-10-2014.
 */
public class Boleta {

    private int id_boleta;
    private String fecha;
    private int subtotal;
    private int total;
    private int descuento_total;
    private int visibilidad;

    public Boleta(){

    }

    public int getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(int id_boleta) {
        this.id_boleta = id_boleta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDescuento_total() {
        return descuento_total;
    }

    public void setDescuento_total(int descuento_total) {
        this.descuento_total = descuento_total;
    }

    public int getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(int visibilidad) {
        this.visibilidad = visibilidad;
    }
}
