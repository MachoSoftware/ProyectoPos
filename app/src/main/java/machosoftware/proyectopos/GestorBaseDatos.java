package machosoftware.proyectopos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by NewOldDeilan on 06-10-2014.
 */
public class GestorBaseDatos extends SQLiteOpenHelper {

    //Nombre de la tabla a la que se está manipulando
    public String tabla;

    public GestorBaseDatos(Context context, String nombre, int version, String tabla) {
        super(context, nombre, null, version);
        this.tabla = tabla;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + this.tabla + " (Id INTEGER, Fecha TEXT, Hora TEXT, Subtotal INTEGER, Descuento INTEGER, Total INTEGER, Visible INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versionAntigua, int versionNueva) {
        Log.w(GestorBaseDatos.class.getName(), "Actializando base de datos desde version: " + versionAntigua + " a " + versionNueva);
        database.execSQL("DROP TABLE IF EXISTS " + this.tabla);
    }

    /*public void insertarFila(String ) {

        //Condicional para cada tabla de la base de datos
        if(this.tabla == "Boletas")
        database.execSQL("INSERT INTO " + this.tabla + "")
    }*/
}
