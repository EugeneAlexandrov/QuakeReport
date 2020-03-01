package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class EventAdapter extends ArrayAdapter<Earthquake> {
    Context mcontext;
    int prev_position = 0;

    public EventAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
        mcontext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = convertView;
        if (listview == null) {
            listview = LayoutInflater.from(mcontext).inflate(R.layout.item, parent, false);
        }
        Earthquake curentEarthquake = getItem(position);
        TextView mag_text = (TextView) listview.findViewById(R.id.mag_text);
        mag_text.setText(Double.toString(curentEarthquake.getmMag()));
        TextView city_text = (TextView) listview.findViewById(R.id.city_text);
        city_text.setText(curentEarthquake.getmCity());
        TextView date_text = (TextView) listview.findViewById(R.id.date_text);
        date_text.setText(Long.toString(curentEarthquake.getmDate()));
        return listview;
    }
}
