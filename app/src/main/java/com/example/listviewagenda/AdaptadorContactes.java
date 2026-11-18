package com.example.listviewagenda;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class AdaptadorContactes extends ArrayAdapter{
    Activity context;
    public AdaptadorContactes(Activity context) {
        super(context, R.layout.listitem_contacte, datosE);
        this.context = (Activity) context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        Log.i("Niko","1");
        // Inflem el Lauoyt
        LayoutInflater inflater = context.getLayoutInflater();
        // Sobre el layout crear (inflat) dupliquem el layour creat amb els objectes, view personals.
        View item = inflater.inflate(R.layout.listitem_titular, null);
        // OJOOOO!!!!! hemos de hacer el findViewById del item que tenemos inflado.
        TextView lblTitulo = (TextView) item.findViewById(R.id.lblTitulo);

        lblTitulo.setText(datosE[position].getTitulo().toString());
        TextView lblSubTitulo = (TextView) item.findViewById(R.id.lblSubtitulo);
        // Log.i("Niko","3->"+datosE[position].getSubtitulo().toString() );
        lblSubTitulo.setText(datosE[position].getSubtitulo().toString());
        // Log.i("Niko","4");
        return (item);
    }
}
