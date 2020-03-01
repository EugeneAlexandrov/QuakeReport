package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class EventAdapter extends ArrayAdapter<Earthquake> {
    Context mcontext;
    int prev_position = 0;
    String place;

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
        Double mag = curentEarthquake.getmMag();
        place = curentEarthquake.getmCity();
        Long date = curentEarthquake.getmDate();

        TextView mag_text = (TextView) listview.findViewById(R.id.mag_text);
        mag_text.setText(Double.toString(mag));

        TextView city_text = (TextView) listview.findViewById(R.id.city_text);

        TextView distance_text = (TextView) listview.findViewById(R.id.distance_text);
        String distance;
        String city;
        if (place.contains(" of")){
           String[] result=place.split(" of");
            distance=result[0]+" of";
            city=result[1];

        } else {
            city=place;
            distance="near the";
        }
        city_text.setText(city);
        distance_text.setText(distance);

        Date dateObject = new Date(curentEarthquake.getmDate());
        String formatedDate = formatDate(dateObject);
        TextView date_text = (TextView) listview.findViewById(R.id.date_text);
        date_text.setText(formatedDate);
        String formatedtime = formatTime(dateObject);
        TextView time_text = (TextView) listview.findViewById(R.id.time_text);
        time_text.setText(formatedtime);
        return listview;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
