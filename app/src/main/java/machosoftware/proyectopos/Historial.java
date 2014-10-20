package machosoftware.proyectopos;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;


public class Historial extends Activity {

    String NombreDatabase = "PosDB4";
    String NombreTabla = "Boletas";
    String OrdenadoPor;
    boolean OrdenAscendente;
    boolean MostrarOcultas = false;
    ListView boletasListView;
    AdaptadorHistorial AdaptadorLista;
    ArrayList<String> Boletas = new ArrayList<String>();
    ArrayList<String> listaVisibilidad = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        AdaptadorLista = new AdaptadorHistorial(this, Boletas, listaVisibilidad);
        boletasListView = (ListView) findViewById(R.id.listaBoletas);
        boletasListView.setAdapter(AdaptadorLista);

        refrescarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.historial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //refrescarLista conecta con la base de datos y llena una lista con los datos especificados
    //con el criterio de busqueda definido en la clase (OrdenadoPor, OrdenAscendente)
    public void refrescarLista() {

        AdaptadorLista.notifyDataSetChanged();
        GestorBaseDatos databaseGestor = new GestorBaseDatos(this, NombreDatabase, 1, NombreTabla);
        SQLiteDatabase database = databaseGestor.getWritableDatabase();
        Cursor cursor;
        if (OrdenAscendente)
            cursor = database.rawQuery("SELECT * FROM Boletas ORDER BY " + OrdenadoPor + " DESC", null);
        else
            cursor = database.rawQuery("SELECT * FROM Boletas ORDER BY " + OrdenadoPor + " ASC", null);

        Boletas.clear();
        listaVisibilidad.clear();
        if (cursor.moveToFirst()) {
            do {
                if (!MostrarOcultas) {
                    if (cursor.getInt(6) == 1) {
                        listaVisibilidad.add(cursor.getString(6));
                        for (int i = 0; i < 6; i++) {
                            Boletas.add(cursor.getString(i));
                        }
                    }
                } else {
                    listaVisibilidad.add(cursor.getString(6));
                    for (int i = 0; i < 6; i++) {
                        Boletas.add(cursor.getString(i));
                    }
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
    }

    //Agregar boleta es un método para pruebas, debe ser eliminado cuando esté implementado el sistema de ventas
    public void agregarBoleta(View view) {

        GestorBaseDatos databaseGestor = new GestorBaseDatos(this, NombreDatabase, 1, NombreTabla);
        SQLiteDatabase database = databaseGestor.getWritableDatabase();

        //Valores aleatorios para testear el campo ID y Total en Boletas
        Random rand = new Random();
        int Id = rand.nextInt(999999);
        int SubTotal = rand.nextInt(999999);
        int Descuento = rand.nextInt(90);
        int Visibilidad = rand.nextInt(2);
        database.execSQL("INSERT INTO Boletas (Id, Fecha, Hora, Subtotal, Descuento, Total, Visible) VALUES (" + Id + ",'10/10/2014', '18:30'," + SubTotal + "," + Descuento + "," + (SubTotal * (100 - Descuento) / 100) +","+ Visibilidad + ")");
        database.close();
        refrescarLista();
    }

    public void OrderBy(View view) {

        //Tomamos el tag del boton apretado y ordenamos la lista segun ese criterio
        //Doble click cambia el orden de ascendencia
        if (OrdenadoPor == view.getTag().toString() && OrdenAscendente) {
            OrdenAscendente = false;
        } else {
            OrdenAscendente = true;
        }

        OrdenadoPor = view.getTag().toString();
        refrescarLista();
    }

    public void mostrarBoletas(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        if(checked)
            MostrarOcultas = true;
        else
            MostrarOcultas = false;

        refrescarLista();
    }
}
