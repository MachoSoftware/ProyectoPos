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

    //sentencia de cracion de tabla
    String tablaCategoria = "CREATE TABLE categoria (nombre TEXT,icono TEXT, visible INTEGER)";


    public PosBaseDatos(Context contexto, String nombre,
                        SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(tablaCategoria);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS categoria");

        //Se crea la nueva versión de la tabla
        db.execSQL(tablaCategoria);
    }

    //============== CATEGORIAS ================
    //Aqui se trabajan las categorias con la bd.

    /**
     * Agrega una categroia a la base de datos.
     * @param entradaNombre nombre de una nueva categroia
     * @param entradaIcono codigo de icono de la categoria
     * @param entradaVisible propiedad de visibilidad de la categroia(para uso aun no definido)
     */
    public void agregarCategoria(String entradaNombre, String entradaIcono, int entradaVisible){

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


}


