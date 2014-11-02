package machosoftware.proyectopos;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Agrega una categoria a la bd.
 * Created by Sebastian on 27-09-2014.
 * estado: NO terminada.
 */
public class Inventario_Categorias_CategoriaAgregar extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_agregar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categoria_agregar, menu);
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
     * Se agregan categorias a la base de datos.
     * @param view
     */
    public void botonAgregarCategoriaAgregar(View view){

        //se obtienen los EditText
        EditText editTextNombre = (EditText) findViewById(R.id.nombreCategoria);
        EditText editTextIcono = (EditText) findViewById(R.id.iconoCategoria);
        //se obtiene el checkbox
        CheckBox checkBoxCategoria = (CheckBox) findViewById(R.id.checkBoxCategoria);
        //se sacan los string
        String stringNombre = editTextNombre.getText().toString();
        String stringIcono = editTextIcono.getText().toString();
        String stringDescripcion = ".";
        //se obtiene el int para la db
        int intVisible;
        if(checkBoxCategoria.isChecked()){
            intVisible = 1;
        }
        else {
            intVisible = 0;
        }

        //verificamos que los string no sea vacio
        if(stringNombre.equals("") || stringIcono.equals("")){
            //avisamos que ingreso datos erroneos...
            Toast.makeText(this,"Datos ingresados erroneos.",Toast.LENGTH_SHORT).show();
        }
        else{
            //llamamos la base de datos
            PosBaseDatos miBase = new PosBaseDatos(getApplicationContext());
            //instanciamos una categoria
            Categoria nuevaCat = new Categoria();
            nuevaCat.setNombre_categoria(stringNombre);
            nuevaCat.setIcono_categoria(stringIcono);
            nuevaCat.setDescripcion(stringDescripcion);
            nuevaCat.setVisibilidad(intVisible);

            //agregamos una categoria...
            miBase.agregarCategoria(nuevaCat);
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
    public void botonAgregarCategoriaCancelar(View view){
        //cierra esta ventana
        this.finish();
    }
}
