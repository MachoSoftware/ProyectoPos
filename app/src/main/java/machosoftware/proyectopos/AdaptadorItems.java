package machosoftware.proyectopos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sebastian on 29-09-2014.
 */
public class AdaptadorItems<String> extends ArrayAdapter<String> {

    private final Context context;
    //strings que muestran nombres de categoria
    private final String[] values;


    public AdaptadorItems(Context context, String[] values) {
        super(context, R.layout.forma_categoria, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //genera la vista de la lista...
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.forma_item, parent, false);
        //conecta el textView del XML de las item...
        TextView textView = (TextView) rowView.findViewById(R.id.nombre_item);
        //setea el string del XML de la item.
        textView.setText(values[position].toString());


        return rowView;
    }
}
