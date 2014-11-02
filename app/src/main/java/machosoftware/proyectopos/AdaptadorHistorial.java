package machosoftware.proyectopos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NewOldDeilan on 09-10-2014.
 */
public class AdaptadorHistorial extends ArrayAdapter<Boleta> {
    private final Context context;
    private final ArrayList<Boleta> listaBoletas;

    public AdaptadorHistorial(Context context, ArrayList<Boleta> listaBoletas) {
        super(context, R.layout.elemento_historial, listaBoletas);
        this.context = context;
        this.listaBoletas = listaBoletas;
    }
    //TESTasddas
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Colores para las boletas ocultas
        int colorFondo = Color.LTGRAY;
        Color colorFuente = new Color();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.elemento_historial, parent, false);

        //Generar tantos TextViews como columnas tenga la Base de Datos
        TextView idView = (TextView) rowView.findViewById(R.id.IdView);
        TextView fechaView = (TextView) rowView.findViewById(R.id.FechaView);
        TextView horaView = (TextView) rowView.findViewById(R.id.HoraView);
        TextView subtotalView = (TextView) rowView.findViewById(R.id.SubtotalView);
        TextView descuentoView = (TextView) rowView.findViewById(R.id.DescuentoView);
        TextView totalView = (TextView) rowView.findViewById(R.id.TotalView);

        //Marcar de color las boletas ocultas
        if (listaBoletas.get(position).getVisibilidad() == 0) {
            idView.setBackgroundColor(colorFondo);
            fechaView.setBackgroundColor(colorFondo);
            horaView.setBackgroundColor(colorFondo);
            subtotalView.setBackgroundColor(colorFondo);
            descuentoView.setBackgroundColor(colorFondo);
            totalView.setBackgroundColor(colorFondo);
        }

        //Setear el texto a todos los text views generados
        idView.setText(Integer.toString(listaBoletas.get(position).getId_boleta()));
        fechaView.setText(listaBoletas.get(position).getFecha().toString());
        horaView.setText(listaBoletas.get(position).getHora().toString());
        subtotalView.setText(Integer.toString(listaBoletas.get(position).getSubtotal()));
        descuentoView.setText(Integer.toString(listaBoletas.get(position).getDescuento_total()));
        totalView.setText(Integer.toString(listaBoletas.get(position).getTotal()));

        return rowView;
    }
}
