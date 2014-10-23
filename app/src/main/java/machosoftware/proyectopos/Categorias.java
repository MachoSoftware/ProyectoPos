package machosoftware.proyectopos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import machosoftware.proyectopos.Interfaces.IConstantes;

/**
 * Categorias del pos.
 * Created by Sebastian on 27-09-2014.
 * estado: NO terminada.
 */
public class Categorias extends Activity implements IConstantes{

    private final int CODIGO_REQUEST = 2501;

    //Aqui pongo un arrayDinamico para poblar con categorias de la BD
    private ArrayList<String> nombresCategorias;
    //Aqui pongo un arrayDinamico para poblar con categorias de la BD
    private ArrayList<String> idIconos = new ArrayList<String>();

    //imagenes de categoria
    private ImageView[] iConosCategorias;


    /**
     * Hace el query a la bd, seleccionando los nombres de la categoria.
     */
    private void refrescarLista(){
        PosBaseDatos miBase = new PosBaseDatos(this, NOMBRE_BD, null, 1);
        SQLiteDatabase db = miBase.getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT nombre FROM categoria", null);
        //nuevo arraylist
        nombresCategorias = new ArrayList<String>();
        //Nos aseguramos de que existe al menos un registro...
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                nombresCategorias.add(c.getString(0));
            } while(c.moveToNext());
        }

        //creamos un array tipo string para que lo lea el Adaptador...
        String[] misArraysStrings = new String[nombresCategorias.size()];
        //obtenemos cada string...
        for(int i=0; i< nombresCategorias.size(); i++){
            misArraysStrings[i] = nombresCategorias.get(i);
        }

        //aqui el adaptador crea la lista con las categorias usando el layout que he creado...
        AdaptadorCategorias<String> adapter = new AdaptadorCategorias<String>(this, misArraysStrings);
        ListView listView = (ListView) findViewById(R.id.listaCategorias);
        listView.setAdapter(adapter);

        //listener para hacerle clic a la categoria
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
                System.out.println("Numero:"+position);
            }
        };
        listView.setOnItemClickListener(mMessageClickedHandler);
        //adapter.notifyDataSetChanged();
        db.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        //hace el query a la bd
        refrescarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categorias, menu);
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
     * Llamada por onClick al agregar una categoria
     * @param view
     */
    public void agregaCategoria(View view){

        Intent intent = new Intent(this, CategoriaAgregar.class);
        //ahora hacemos un lanzamiento con espera de resultado...
        startActivityForResult(intent, CODIGO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //codigo referencia usado cuando se quiere devolver datos!
        //if(requestCode == CODIGO_REQUEST && resultCode == RESULT_OK){
        //    //aqui s ehace lo que se tenga que hacer!
        //    Toast.makeText(this,"activity result!",Toast.LENGTH_SHORT).show();
        //}
        //aqui refrescamos la viewList haciendo query a la bd
        refrescarLista();
    }
}
