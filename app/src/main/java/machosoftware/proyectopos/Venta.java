package machosoftware.proyectopos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class Venta extends Activity {

    ArrayList<VentaItem> ListaItems = new ArrayList<VentaItem>();
    ListView itemsListView;
    AdaptadorVenta adaptadorLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        adaptadorLista = new AdaptadorVenta(this, ListaItems);
        itemsListView = (ListView) findViewById(R.id.listViewItems);
        itemsListView.setAdapter(adaptadorLista);

        refrescarLista();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.venta, menu);
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

    public void refrescarLista() {
        adaptadorLista.notifyDataSetChanged();
    }

    public void agregarItem(View view) {
        ListaItems.add(new VentaItem("Item", 10, 990990, 10));
        refrescarLista();
    }

    public void confirmarVenta(View view) {

    }
}
