package machosoftware.proyectopos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Agrega un Item a la bd.
 * Created by Sebastian on 27-09-2014.
 * estado: NO terminada.
 */
public class Inventario_Items_ItemsAgregar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.print("asasdsdaasd");
        setContentView(R.layout.activity_items_agregar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.items_agregar, menu);
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

    /**
     * Se agregan items a la base de datos.
     * @param view
     */
    public void botonAgregarItemsAgregar(View view){

        //se obtienen los EditText
        EditText editTextNombre = (EditText) findViewById(R.id.nombreItem);
        EditText editTextIcono = (EditText) findViewById(R.id.iconoItem);

        //se sacan los string
        String stringNombre = editTextNombre.getText().toString();
        String stringIcono = editTextIcono.getText().toString();
        //AGREGAR SPINNER Y OTRAS COSAS ACA
        //... hacer popup!

        //variable int auxiliar.. hasta que el arruya termine la wea....
        int aux = 1;

        //verificamos que los string no sea vacio
        if(stringNombre.equals("") || stringIcono.equals("")){
            //avisamos que ingreso datos erroneos...
            Toast.makeText(this, "Datos ingresados erroneos.", Toast.LENGTH_SHORT).show();
        }
        else{
            //llamamos la base de datos
            PosBaseDatos miBase = new PosBaseDatos(getApplicationContext());
            //instanciamos un item
            Item nuevoItem = new Item();
            nuevoItem.setNombre_item(stringNombre);
            nuevoItem.setIcono_item(stringIcono);
            nuevoItem.setId_cat1(aux);
            nuevoItem.setId_cat2(aux);
            nuevoItem.setId_cat3(aux);
            nuevoItem.setTipo(aux);
            nuevoItem.setPrecio(aux);
            nuevoItem.setStock_actual(aux);
            nuevoItem.setStock_alerta(aux);
            nuevoItem.setStock_optimo(aux);

            //agregamos el item con los datos seteados
            miBase.agregarItem(nuevoItem);
                    //codigo para reuso en caso de que se quiera enviar informacion!
                    //Intent intento = new Intent();
                    //setResult(RESULT_OK,intento);
                    //cierra esta ventana
            this.finish();
        }
    }

    /**
     * Para volver al listview...
     * @param view
     */
    public void botonAgregarItemsCancelar(View view){
        //cierra esta ventana
        this.finish();
    }
}
