package com.example.myloginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapterReservaciones extends ArrayAdapter<Reservacion> {
    private List<Reservacion> reservacionesArray = new ArrayList<>();

    public ListViewAdapterReservaciones(Context context, int reservaciones_listview_template, List<Reservacion> datos){
        super(context, R.layout.reservaciones_listview_template, datos);

        reservacionesArray = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.reservaciones_listview_template, null);

        TextView lblCodSalon = (TextView)item.findViewById(R.id.lblCodSalon);
        lblCodSalon.setText(reservacionesArray.get(position).getCodSalon());

        TextView lblTiempoInicio = (TextView)item.findViewById(R.id.lblTiempoInicio);
        lblTiempoInicio.setText(reservacionesArray.get(position).getTiempoInicio());

        TextView lblTiempoFinal = (TextView)item.findViewById(R.id.lblTiempoFinal);
        lblTiempoFinal.setText(reservacionesArray.get(position).getTiempoFinal());

        return(item);
    }
}
