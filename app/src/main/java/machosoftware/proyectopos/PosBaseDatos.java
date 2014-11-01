package machosoftware.proyectopos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
            "(" + KEY_ID_ITEM + "INTEGER PRIMARY KEY autoincrement," + KEY_ID_CAT1 + "INTEGER,"
            + KEY_ID_CAT2 + "INTEGER," + KEY_ID_CAT3 + "INTEGER," + KEY_NOMBRE_ITEM + "TEXT,"
            + KEY_ICONO_ITEM + "TEXT," + KEY_TIPO + "INTEGER," + KEY_PRECIO + "INTEGER,"
            + KEY_STOCK_ACTUAL + "INTEGER," + KEY_STOCK_OPTIMO + "INTEGER," + KEY_STOCK_ALERTA + "INTEGER)";

    // sentencias de creacion : categoria

    private static final String CREATE_TABLE_CATEGORIA ="CREATE TABLE" + TABLA_CATEGORIA +
            "(" + KEY_ID_CATEGORIA + "INTEGER PRIMARY KEY autoincrement," + KEY_NOMBRE_CATEGORIA + "TEXT,"
            + KEY_ICONO_CATEGORIA + "TEXT," + KEY_DESCRIPCION + "TEXT," + KEY_CATEGORIA_VISIBILIDAD+ "INTEGER)";

    // sentencias de creacion : boleta
    // FECHA Y HORA SON TRATADOS COMO TEXT, PROXIMA MIGRACION A DATATIME O DATE

    private static final String CREATE_TABLE_BOLETA ="CREATE TABLE" + TABLA_BOLETA +
            "(" + KEY_ID_BOLETA + "INTEGER PRIMARY KEY autoincrement," + KEY_FECHA + "TEXT,"
            + KEY_HORA + "TEXT," + KEY_BOLETA_SUBTOTAL + "INTEGER," + KEY_BOLETA_TOTAL + "INTEGER,"
            + KEY_DESCUENTOTOTAL + "INTEGER,"+ KEY_BOLETA_VISIBILIDAD + "INTEGER)";


    // sentencias de creacion : boleta_detalle
    // FALTAAAAAAAAAAAAAAAAA CLAVE FOREANEAS
    // FALTAAAAAAAAA CLAVE FOREANEAS

    private static final String CREATE_TABLE_BOLETA_DETALLE ="CREATE TABLE" + TABLA_BOLETA_DETALLE +
            "(" + KEY_DETALLE_ID_BOLETA + "INTEGER," + KEY_DETALLE_ID_ITEM+ "INTEGER," + KEY_CANTIDAD + "INTEGER,"
            + KEY_BOLETA_DETALLE_SUBTOTAL + "INTEGER," + KEY_DESCUENTO + "INTEGER," + KEY_BOLETA_DETALLE_TOTAL + "INTEGER)";




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
    // agregar categoria
    /*
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
*/
}


