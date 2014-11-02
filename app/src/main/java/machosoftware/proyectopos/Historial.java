package machosoftware.proyectopos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;


public class Historial extends Activity {

    String ordenadoPor;
    boolean ordenAscendente;
    boolean mostrarOcultas = false;
    ListView boletasListView;
    AdaptadorHistorial adaptadorLista;
    ArrayList<Boleta> Boletas = new ArrayList<Boleta>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        adaptadorLista = new AdaptadorHistorial(this, Boletas);
        boletasListView = (ListView) findViewById(R.id.listaBoletas);
        boletasListView.setAdapter(adaptadorLista);

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

        adaptadorLista.notifyDataSetChanged();
        PosBaseDatos databaseGestor = new PosBaseDatos(getApplicationContext());
        Boletas.addAll(databaseGestor.obtenerBoletas(ordenadoPor, ordenAscendente, mostrarOcultas));

    }

    //Agregar boleta es un método para pruebas, debe ser eliminado cuando esté implementado el sistema de ventas
    public void agregarBoleta(View view) {

        Boletas.clear();
        PosBaseDatos databaseGestor = new PosBaseDatos(getApplicationContext());
        Boleta BoletaPrueba = new Boleta();
        BoletaPrueba.setVisibilidad(1);
        BoletaPrueba.setTotal(1000);
        BoletaPrueba.setHora("20:30");
        BoletaPrueba.setFecha("10/10/2014");
        BoletaPrueba.setDescuento_total(20);
        BoletaPrueba.setSubtotal(2000);
        databaseGestor.agregarBoleta(BoletaPrueba);
        refrescarLista();
    }

    public void OrderBy(View view) {

        //Tomamos el tag del boton apretado y ordenamos la lista segun ese criterio
        //Doble click cambia el orden de ascendencia
        if (ordenadoPor == view.getTag().toString() && ordenAscendente) {
            ordenAscendente = false;
        } else {
            ordenAscendente = true;
        }

        ordenadoPor = view.getTag().toString();
        refrescarLista();
    }

    public void mostrarBoletas(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        if(checked)
            mostrarOcultas = true;
        else
            mostrarOcultas = false;

        refrescarLista();
    }
}
