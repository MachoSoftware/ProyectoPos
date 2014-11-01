package machosoftware.proyectopos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NewOldDeilan on 24-10-2014.
 */
public class AdaptadorVenta extends ArrayAdapter<VentaItem>{
    private final Context context;
    private final ArrayList<VentaItem> listaItems;

    public AdaptadorVenta(Context context, ArrayList<VentaItem> listaItems) {
        super(context, R.layout.elemento_historial, listaItems);
        this.context = context;
        this.listaItems = listaItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.elemento_venta, parent, false);

        //Generar tantos TextViews como columnas tenga la Base de Datos
        TextView itemNombreView = (TextView) rowView.findViewById(R.id.ItemNombreView);
        TextView itemCantidadView = (TextView) rowView.findViewById(R.id.ItemCantidadView);
        TextView itemPrecioView = (TextView) rowView.findViewById(R.id.ItemPrecioView);

        //Setear el texto a todos los text views generados
        itemNombreView.setText(listaItems.get(position).getNombre().toString());
        itemCantidadView.setText(Integer.toString(listaItems.get(position).getCantidad()));
        itemPrecioView.setText(Integer.toString(listaItems.get(position).getPrecio()));


        return rowView;
    }
}
