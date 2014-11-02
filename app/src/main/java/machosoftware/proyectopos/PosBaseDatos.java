package machosoftware.proyectopos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Crea y modifica base de datos, con informacion del pos.
 * Created by Sebastian on 27-09-2014.
 * estado: NO terminada.
 */
public class PosBaseDatos extends SQLiteOpenHelper {

    // Para el log cat
    private static final String LOG = "BDGESTOR";



    // Version BD
    private static final int DATABASE_VERSION = 1;

    // Nombre BD
    private static final String DATABASE_NOMBRE = "posDB";

    // Nombre tablas
    private static final String TABLA_ITEM = "item";
    private static final String TABLA_CATEGORIA = "categoria";
    private static final String TABLA_BOLETA ="boleta";
    private static final String TABLA_BOLETA_DETALLE ="boleta_detalle";

    // item- nombre columnas
    private static final String KEY_ID_ITEM = "id_item";
    private static final String KEY_ID_CAT1 = "id_cat1";
    private static final String KEY_ID_CAT2 = "id_cat2";
    private static final String KEY_ID_CAT3 = "id_cat3";
    private static final String KEY_NOMBRE_ITEM = "nombre_item";
    private static final String KEY_ICONO_ITEM = "icono_item";
    private static final String KEY_TIPO = "tipo";
    private static final String KEY_PRECIO = "precio";
    private static final String KEY_STOCK_ACTUAL = "stock_actual";
    private static final String KEY_STOCK_OPTIMO = "stock_optimo";
    private static final String KEY_STOCK_ALERTA = "stock_alerta";

    // categoria- nombre columnas

    private static final String KEY_ID_CATEGORIA = "id_categoria";
    private static final String KEY_NOMBRE_CATEGORIA = "nombre_categoria";
    private static final String KEY_ICONO_CATEGORIA = "icono_categoria";
    private static final String KEY_DESCRIPCION = "descripcion";
    private static final String KEY_CATEGORIA_VISIBILIDAD = "categoria_visibilidad";

    // boleta- nombre columnas
    private static final String KEY_ID_BOLETA = "id_boleta";
    private static final String KEY_FECHA = "fecha";
    private static final String KEY_HORA = "hora";
    private static final String KEY_BOLETA_SUBTOTAL = "boleta_subtotal";
    private static final String KEY_BOLETA_TOTAL = "boleta_total";
    private static final String KEY_DESCUENTOTOTAL = "descuentototal";
    private static final String KEY_BOLETA_VISIBILIDAD = "boleta_visibilidad";

    // boleta_detalle- nombre columnas

    private static final String KEY_DETALLE_ID_ITEM = "detalle_id_item";
    private static final String KEY_DETALLE_ID_BOLETA = "detalle_id_boleta";
    private static final String KEY_CANTIDAD = "cantidad";
    private static final String KEY_BOLETA_DETALLE_SUBTOTAL = "boleta_detalle_subtotal";
    private static final String KEY_DESCUENTO = "descuento";
    private static final String KEY_BOLETA_DETALLE_TOTAL = "boleta_detalle_total";

    // sentencias de creacion : item
    private static final String CREATE_TABLE_ITEM ="CREATE TABLE "+ TABLA_ITEM +
            " ( " + KEY_ID_ITEM + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ID_CAT1 + " INTEGER,"
            + KEY_ID_CAT2 + " INTEGER," + KEY_ID_CAT3 + " INTEGER," + KEY_NOMBRE_ITEM + " TEXT,"
            + KEY_ICONO_ITEM + " TEXT," + KEY_TIPO + " INTEGER," + KEY_PRECIO + " INTEGER,"
            + KEY_STOCK_ACTUAL + " INTEGER," + KEY_STOCK_OPTIMO + " INTEGER," + KEY_STOCK_ALERTA + " INTEGER)";

    // sentencias de creacion : categoria
    private static final String CREATE_TABLE_CATEGORIA ="CREATE TABLE " + TABLA_CATEGORIA +
            "( " + KEY_ID_CATEGORIA + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NOMBRE_CATEGORIA + " TEXT,"
            + KEY_ICONO_CATEGORIA + " TEXT," + KEY_DESCRIPCION + " TEXT," + KEY_CATEGORIA_VISIBILIDAD+ " INTEGER)";

    // sentencias de creacion : boleta
    // FECHA Y HORA SON TRATADOS COMO TEXT, PROXIMA MIGRACION A DATATIME O DATE

    private static final String CREATE_TABLE_BOLETA ="CREATE TABLE " + TABLA_BOLETA +
            " ( " + KEY_ID_BOLETA + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FECHA + " TEXT,"
            + KEY_HORA + " TEXT," + KEY_BOLETA_SUBTOTAL + " INTEGER," + KEY_BOLETA_TOTAL + " INTEGER,"
            + KEY_DESCUENTOTOTAL + " INTEGER,"+ KEY_BOLETA_VISIBILIDAD + " INTEGER)";


    // sentencias de creacion : boleta_detalle
    // FALTAAAAAAAAAAAAAAAAA CLAVE FOREANEAS
    // FALTAAAAAAAAA CLAVE FOREANEAS

    private static final String CREATE_TABLE_BOLETA_DETALLE ="CREATE TABLE " + TABLA_BOLETA_DETALLE +
            " ( " + KEY_DETALLE_ID_BOLETA + " INTEGER," + KEY_DETALLE_ID_ITEM+ " INTEGER," + KEY_CANTIDAD + " INTEGER,"
            + KEY_BOLETA_DETALLE_SUBTOTAL + " INTEGER," + KEY_DESCUENTO + " INTEGER," + KEY_BOLETA_DETALLE_TOTAL + " INTEGER)";




    public PosBaseDatos(Context contexto) {
        super(contexto, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }


   /*  VERSION ANTERIOR REVISAR DEFERENCIAS public PosBaseDatos(Context contexto, String nombre,
                        SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }*/


    @Override
    public void onCreate(SQLiteDatabase BD) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        BD.execSQL(CREATE_TABLE_BOLETA);
        BD.execSQL(CREATE_TABLE_ITEM);
        BD.execSQL(CREATE_TABLE_CATEGORIA);
        BD.execSQL(CREATE_TABLE_BOLETA_DETALLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        DB.execSQL("DROP TABLE IF EXISTS " + TABLA_ITEM);
        DB.execSQL("DROP TABLE IF EXISTS " + TABLA_BOLETA);
        DB.execSQL("DROP TABLE IF EXISTS " + TABLA_CATEGORIA);
        DB.execSQL("DROP TABLE IF EXISTS " + TABLA_BOLETA_DETALLE);

        //Se crea la nueva versión de las tablas
        onCreate(DB);
    }


/*
  este comentado esta raro y solo abre y no cierra, al momento de borrar solo borrar esta llave.....
    //============== CATEGORIAS ================
    //Aqui se trabajan las categorias con la bd.

    /**
     * Agrega una categroia a la base de datos.
     * @param entradaNombre nombre de una nueva categroia
     * @param entradaIcono codigo de icono de la categoria
     * @param entradaVisible propiedad de visibilidad de la categroia(para uso aun no definido)
     */
   /* public void agregarCategoria(String entradaNombre, String entradaIcono, int entradaVisible){

        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null)
        {
            System.out.println(" -----> OJO: 'entradaVisible' aun no esta siendo ingresada...");
            db.execSQL("INSERT INTO categoria (nombre, icono, visible) VALUES ('"+entradaNombre+"','"+entradaIcono+"', "+entradaVisible+")");
            db.close();
        }
    }

    public void borraCategoria(){

    }
    public void modificarCategoria(){

    }

    //============== ITEMS =====================
    //aqui se trabajan los items con la bd...

    public void agregarItem(){

    }
    public void borrarItem(){

    }
    public void modificarItem(){

    }

*/

    //  Funciones varias- CRUD(Create, Read, Update, Delete)

    /**
     * @brief Agrega una categoria a la base de datos.
     * @param categoria Categoria que se agrega a la base de datos.
     */
    public void agregarCategoria (Categoria categoria) {
        SQLiteDatabase BD = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE_CATEGORIA, categoria.getNombre_categoria()); // Categoria nombre
        values.put(KEY_ICONO_CATEGORIA, categoria.getIcono_categoria()); // Categoria icono
        values.put(KEY_DESCRIPCION, categoria.getDescripcion()); // Categoria descripcion
        values.put(KEY_CATEGORIA_VISIBILIDAD, categoria.getVisibilidad()); // Categoria visibilidad

        // Insertar
        BD.insert(TABLA_CATEGORIA, null, values);
        BD.close(); // siempre cerrar la bd
    }

    /**
     * @brief Se obtienen las categorias de la base de datos.
     *
     * Se obtiene un ArrayList de categorias de la base de datos.
     *
     * @return Retorna ArrayList<Categoria> con las categorias de la base de datos.
     */
    public ArrayList<Categoria> obtenerCategorias () {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>(); // array
        String Query = "SELECT * FROM " + TABLA_CATEGORIA ; //  query para rescata all
        Log.e(LOG, Query);
        SQLiteDatabase BD = this.getReadableDatabase();
        Cursor c = BD.rawQuery(Query, null);
        // loop sobRE Cada respuesta
        if (c.moveToFirst()){
           do {
               Categoria categoria = new Categoria();
               categoria.setId_Categoria(c.getInt((c.getColumnIndex(KEY_ID_CATEGORIA))));
               categoria.setNombre_categoria(c.getString((c.getColumnIndex(KEY_NOMBRE_CATEGORIA))));
               categoria.setIcono_categoria(c.getString((c.getColumnIndex(KEY_ICONO_CATEGORIA))));
               categoria.setDescripcion(c.getString((c.getColumnIndex(KEY_DESCRIPCION))));
               categoria.setVisibilidad(c.getInt((c.getColumnIndex(KEY_CATEGORIA_VISIBILIDAD))));
               categorias.add(categoria);
           } while(c.moveToNext());
        }
        c.close();
        BD.close();
        return categorias;
    }

    /**
     * @brief Agrega un item a la base de datos.
     * @param item Item que se agrega a la base de datos.
     */
    public void agregarItem(Item item){

        SQLiteDatabase BD = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_CAT1, item.getId_cat1()); // Item categoria 1
        values.put(KEY_ID_CAT2, item.getId_cat2()); // Item categoria 2
        values.put(KEY_ID_CAT3, item.getId_cat3()); // Item categoria 3
        values.put(KEY_NOMBRE_ITEM, item.getNombre_item()); // Item nombre
        values.put(KEY_ICONO_ITEM, item.getIcono_item()); // Item icono
        values.put(KEY_TIPO, item.getTipo()); // item tipo
        values.put(KEY_PRECIO, item.getPrecio()); // item precio
        values.put(KEY_STOCK_ACTUAL, item.getStock_actual()); // item stock actual
        values.put(KEY_STOCK_OPTIMO, item.getStock_optimo()); // item stock optimo
        values.put(KEY_STOCK_ALERTA, item.getStock_alerta()); // item stock alerta
        // insert valores
        BD.insert(TABLA_ITEM, null, values);
        BD.close(); // siempre cerrar
    }

    /**
     * @brief Se obtienen los items de la base de datos.
     *
     * Se obtiene un ArrayList de items de la base de datos.
     *
     * @return Retorna ArrayList<Item> con los items de la base de datos.
     */
    public ArrayList<Item> obtenerItems () {
        ArrayList<Item> items = new ArrayList<Item>();// inicia array
        String Query = "SELECT * FROM " + TABLA_ITEM; // Query rescatar todos los items
        Log.e(LOG, Query);
        SQLiteDatabase BD = this.getWritableDatabase();
        Cursor c = BD.rawQuery(Query, null);

        // loop sobre cada raw
        if (c.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId_Item(c.getInt(c.getColumnIndex(KEY_ID_ITEM)));
                item.setId_cat1(c.getInt(c.getColumnIndex(KEY_ID_CAT1)));
                item.setId_cat2(c.getInt(c.getColumnIndex(KEY_ID_CAT2)));
                item.setId_cat3(c.getInt(c.getColumnIndex(KEY_ID_CAT3)));
                item.setNombre_item(c.getString(c.getColumnIndex(KEY_NOMBRE_ITEM)));
                item.setIcono_item(c.getString(c.getColumnIndex(KEY_ICONO_ITEM)));
                item.setTipo(c.getInt(c.getColumnIndex(KEY_TIPO)));
                item.setPrecio(c.getInt(c.getColumnIndex(KEY_PRECIO)));
                item.setStock_actual(c.getInt(c.getColumnIndex(KEY_STOCK_ACTUAL)));
                item.setStock_optimo(c.getInt(c.getColumnIndex(KEY_STOCK_OPTIMO)));
                item.setStock_alerta(c.getInt(c.getColumnIndex(KEY_STOCK_ALERTA)));

                items.add(item);


            } while (c.moveToNext());

        }
        c.close();
        BD.close();
        return items;
   }
}


