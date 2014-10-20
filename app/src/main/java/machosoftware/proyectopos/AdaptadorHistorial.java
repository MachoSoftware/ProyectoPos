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
 * <p/>
 * Adaptador de historial
 * Funciona con 2 listas de string:
 * - La información en si (values1)
 * - Lista indice con el parametro de visibilidad (listaVisibilidad)
 * <p/>
 * El list view trabaja con la lista indice de visibilidad para generar tantas views como
 * filas haya en la base de datos
 * <p/>
 * Luego el adaptador genera TextViews por cada campo de la base de datos, para eso
 * es la lista "values1"
 */
public class AdaptadorHistorial<String> extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values1;
    private final ArrayList<String> listaVisibilidad;

    public AdaptadorHistorial(Context context, ArrayList<String> values1, ArrayList<String> listaVisibilidad) {
        super(context, R.layout.elemento_historial, listaVisibilidad);
        this.context = context;
        this.values1 = values1;
        this.listaVisibilidad = listaVisibilidad;
    }
    //TESTasddas
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Colores para las boletas ocultas
        int colorFondo = Color.LTGRAY;
        Color colorFuente = new Color();

        //Index para mostrar las información de la lista
        int dataIndex = position;
        if ((position * 6) + 5 < values1.size())
            dataIndex *= 6;

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
        if (Integer.parseInt(listaVisibilidad.get(position).toString()) == 0) {
            idView.setBackgroundColor(colorFondo);
            fechaView.setBackgroundColor(colorFondo);
            horaView.setBackgroundColor(colorFondo);
            subtotalView.setBackgroundColor(colorFondo);
            descuentoView.setBackgroundColor(colorFondo);
            totalView.setBackgroundColor(colorFondo);
        }

        //Setear el texto a todos los text views generados
        idView.setText(values1.get(dataIndex).toString());
        fechaView.setText(values1.get(dataIndex + 1).toString());
        horaView.setText(values1.get(dataIndex + 2).toString());
        subtotalView.setText(values1.get(dataIndex + 3).toString());
        descuentoView.setText(values1.get(dataIndex + 4).toString());
        totalView.setText(values1.get(dataIndex + 5).toString());

        return rowView;
    }
}
