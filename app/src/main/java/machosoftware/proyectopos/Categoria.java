package machosoftware.proyectopos;

/**
 * Created by Sebastian on 27-10-2014.
 */
public class Categoria {

    private int Id_Categoria;
    private String nombre_categoria;
    private String icono_categoria;
    private String descripcion;
    private int visibilidad;

    public Categoria(){

    }

    public int getId_Categoria() {
        return Id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        Id_Categoria = id_Categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getIcono_categoria() {
        return icono_categoria;
    }

    public void setIcono_categoria(String icono_categoria) {
        this.icono_categoria = icono_categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(int visibilidad) {
        this.visibilidad = visibilidad;
    }
}
