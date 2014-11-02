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
 * Inventario_Categorias del pos.
 * Created by Sebastian on 27-09-2014.
 * estado: NO terminada.
 */
public class Inventario_Categorias extends Activity implements IConstantes{

    private final int CODIGO_REQUEST = 2501;

    //array dinamico de categorias para poblar con la info de la db
    private ArrayList<Categoria> categorias;
    //Aqui pongo un arrayDinamico para poblar con categorias de la BD
    private ArrayList<String> idIconos = new ArrayList<String>();

    //imagenes de categoria
    private ImageView[] iConosCategorias;


    /**
     * Hace el query a la bd, seleccionando los nombres de la categoria.
     */
    private void refrescarLista(){
        PosBaseDatos miBase = new PosBaseDatos(getApplicationContext());

        //se instancia la lista de objetos de categoria
        categorias = miBase.obtenerCategorias();
        //en caso de que falle hay que hacer algo....
        if(categorias.isEmpty()){
            System.out.println("Lista vacia!!!!!!!");
        }

        //creamos un array tipo string para que lo lea el Adaptador...
        String[] misArraysStrings = new String[categorias.size()];
        //obtenemos cada string...
        for(int i=0; i< categorias.size(); i++){
            misArraysStrings[i] = categorias.get(i).getNombre_categoria();
        }
        //obtenemos cada codigo del icono
        //for...

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

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_categorias);
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

        Intent intent = new Intent(this, Inventario_Categorias_CategoriaAgregar.class);
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
