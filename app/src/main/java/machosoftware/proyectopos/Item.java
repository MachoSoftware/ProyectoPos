package machosoftware.proyectopos;

/**
 * Created by Sebastian on 27-10-2014.
 *
 * @brief Producto vendible.
 */
public class Item {

    private int Id_Item;
    private int Id_cat1;
    private int Id_cat2;
    private int Id_cat3;
    private String nombre_item;
    private String icono_item;
    private int tipo;
  //private int tipo_especial; <-- ya no va este!
    private int precio;
    private int stock_actual;
    private int stock_optimo;
    private int stock_alerta;

    //constr
    public Item(){

    }
    //constructor para lista
    public Item(int id_Item, String nombre, String iconoItem) {
        Id_Item = id_Item;
        this.nombre_item = nombre;
        this.icono_item = iconoItem;
    }

    public int getId_Item() {
        return Id_Item;
    }

    public void setId_Item(int id_Item) {
        Id_Item = id_Item;
    }

    public int getId_cat1() {
        return Id_cat1;
    }

    public void setId_cat1(int id_cat1) {
        Id_cat1 = id_cat1;
    }

    public int getId_cat2() {
        return Id_cat2;
    }

    public void setId_cat2(int id_cat2) {
        Id_cat2 = id_cat2;
    }

    public int getId_cat3() {
        return Id_cat3;
    }

    public void setId_cat3(int id_cat3) {
        Id_cat3 = id_cat3;
    }

    public String getNombre_item() {
        return nombre_item;
    }

    public void setNombre_item(String nombre_item) {
        this.nombre_item = nombre_item;
    }

    public String getIcono_item() {
        return icono_item;
    }

    public void setIcono_item(String icono_item) {
        this.icono_item = icono_item;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }


    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public int getStock_optimo() {
        return stock_optimo;
    }

    public void setStock_optimo(int stock_optimo) {
        this.stock_optimo = stock_optimo;
    }

    public int getStock_alerta() {
        return stock_alerta;
    }

    public void setStock_alerta(int stock_alerta) {
        this.stock_alerta = stock_alerta;
    }
}
