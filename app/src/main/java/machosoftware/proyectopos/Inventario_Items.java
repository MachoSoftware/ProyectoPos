package machosoftware.proyectopos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import machosoftware.proyectopos.Interfaces.IConstantes;


public class Inventario_Items extends Activity implements IConstantes {

    //codigo arbitrario apra hacer respuestas... ahora no es necesario.
    private final int CODIGO_REQUEST = 25023;

    //Aqui pongo un arrayDinamico para poblar con items de la BD
    private ArrayList<Item> productos;

    /**
     * Hace el query a la bd, seleccionando los nombres del item.
     */
    private void refrescarLista(){

        //instancia bd
        PosBaseDatos miBase = new PosBaseDatos(getApplicationContext());
        //array de items
        productos = miBase.obtenerItems();
        if(productos.isEmpty()){
            System.out.println("Productos vacía!");
        }

        //creamos un array tipo string para que lo lea el Adaptador...
        String[] misArraysStrings = new String[productos.size()];

        //obtenemos cada string de nombres...
        for(int i=0; i< productos.size(); i++){
            misArraysStrings[i] = productos.get(i).getNombre_item();
        }
        //obtenemos cada codigo del icono
        /***
         * Aqui se debe hacer su for para obtener los codigos de los iconos a mostrar
         * en el adaptador!
         * Se hace de similar manera que arribita en "misArraysStrings"
         */

        //aqui el adaptador crea la lista con las categorias usando el layout que se ha creado...
        AdaptadorItems<String> adapter = new AdaptadorItems<String>(this, misArraysStrings);
        ListView listView = (ListView) findViewById(R.id.listaItems);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_items);
        //muestra lista
        refrescarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.items, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
                /***
                 //codigo referencia usado cuando se quiere devolver datos!
                 //no borrar! (preguntar al araya)
                 if(requestCode == CODIGO_REQUEST && resultCode == RESULT_OK){
                 //aqui s ehace lo que se tenga que hacer!
                 Toast.makeText(this,"activity result!",Toast.LENGTH_SHORT).show();
                 }
                 ***/
        //aqui refrescamos la viewList haciendo query a la bd
        refrescarLista();
    }

    /**
     * Llamada por onClick al agregar una categoria
     * @param view
     */
    public void agregaItem(View view){

        Intent intent = new Intent(this, Inventario_Items_ItemsAgregar.class);
        //ahora hacemos un lanzamiento con espera de resultado...
        startActivityForResult(intent, CODIGO_REQUEST);
    }
}
