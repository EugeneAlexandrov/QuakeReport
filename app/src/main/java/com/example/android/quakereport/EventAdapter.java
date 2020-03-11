package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        place = curentEarthquake.getmCity();
        Long date = curentEarthquake.getmDate();

        TextView mag_text = (TextView) listview.findViewById(R.id.mag_text);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag_text.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(curentEarthquake.getmMag());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(curentEarthquake.getmMag());
        mag_text.setText(output);

        TextView city_text = (TextView) listview.findViewById(R.id.city_text);
        TextView distance_text = (TextView) listview.findViewById(R.id.distance_text);
        String distance;
        String city;
        if (place.contains(" of ")) {
            String[] result = place.split(" of ");
            distance = result[0] + " of";
            city = result[1];

        } else {
            city = place;
            distance = "near the";
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

    private int getMagnitudeColor(double getmMag) {
        int color = 0;
        switch ((int) getmMag) {
            case 0:
            case 1:
                color = R.color.magnitude1;
                break;
            case 2:
                color = R.color.magnitude2;
                break;
            case 3:
                color = R.color.magnitude3;
                break;
            case 4:
                color = R.color.magnitude4;
                break;
            case 5:
                color = R.color.magnitude5;
            case 6:
                color = R.color.magnitude6;
                break;
            case 7:
                color = R.color.magnitude7;
                break;
            case 8:
                color = R.color.magnitude8;
                break;
            case 9:
                color = R.color.magnitude9;
                break;
            case 10:
                color = R.color.magnitude10plus;
                break;
            default:
                color=R.color.magnitude2;
                break;
        }
        return ContextCompat.getColor(getContext(), color);
    }


    /**
     * Return the formatted date string (i.e. "jan 31, 2020") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM);
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
