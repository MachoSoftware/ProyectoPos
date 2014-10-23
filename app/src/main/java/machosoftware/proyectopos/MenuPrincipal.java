package machosoftware.proyectopos;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

<<<<<<< HEAD:app/src/main/java/machosoftware/proyectopos/Main.java
//DELETE

public class Main extends Activity {
=======
/**
 * Menu principal.
 * Created by Sebastian on 27-09-2014.
 */
public class MenuPrincipal extends Activity {
>>>>>>> 1354a100c283b0af2849336561e6fe54548443aa:app/src/main/java/machosoftware/proyectopos/MenuPrincipal.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
     * abre el inventario
     * @param view
     */
    public void abreInventario(View view){
        Intent intent = new Intent(this, Inventario.class);
        startActivity(intent);
    }
}
