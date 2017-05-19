package com.example.mwi.weatherapp.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mwi.weatherapp.Model.Cuaca;
import com.example.mwi.weatherapp.R;

import java.util.ArrayList;

/**
 * Created by mwi on 5/19/17.
 */

public class AdapterCuaca extends BaseAdapter {
    private Context context;
    private ArrayList<Cuaca> dataCuaca;

    public AdapterCuaca(Context context, ArrayList<Cuaca> dataCuaca) {
        this.context = context;
        this.dataCuaca = dataCuaca;
    }

    @Override
    public int getCount() {
        return dataCuaca.size();
    }

    @Override
    public Cuaca getItem(int position) {
        return dataCuaca.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v;

        v = inflater.inflate(R.layout.list_row_cuaca, parent, false);
        TextView derajat = (TextView) v.findViewById(R.id.txtDerajat);
        TextView tanggal = (TextView) v.findViewById(R.id.txtTanggal);
        TextView status = (TextView) v.findViewById(R.id.txtStatus);

        derajat.setText(Integer.toString(getItem(position).getDerajat()) + (char) 0x00B0);
        tanggal.setText(getItem(position).getTanggal());
        status.setText(getItem(position).getStatus());
        return v;
    }
}
