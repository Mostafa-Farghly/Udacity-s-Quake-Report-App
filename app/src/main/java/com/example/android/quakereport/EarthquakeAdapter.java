package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.DecimalFormat;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param earthquakes   A List of {@link Earthquake} objects to display in a list
     */
    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Inflate a new view if there is no view available to reuse.
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the current {@link Earthquake} object
        Earthquake earthquake = getItem(position);

        // Initialize a magnitude decimal formatter
        DecimalFormat magFormat = new DecimalFormat("0.0");

        // Set the magnitude
        TextView magTextView = listItemView.findViewById(R.id.magnitude_textview);
        magTextView.setText(magFormat.format(earthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Get the place and split it to location and its offset
        String place = earthquake.getPlace();
        String[] placeParts;

        if(place.contains(" of ")) {
            placeParts = place.split("(?<= of )", 2);
        } else {
            placeParts = new String[2];
            placeParts[0] = getContext().getString(R.string.near_the);;
            placeParts[1] = place;
        }

        // Set the location offset
        TextView locationOffsetTextView = listItemView.findViewById(R.id.location_offset_textview);
        locationOffsetTextView.setText(placeParts[0]);

        // Set the location
        TextView locationTextView = listItemView.findViewById(R.id.location_textview);
        locationTextView.setText(placeParts[1]);

        // Initialize date and time formatters
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a");

        // Get date and time
        Date date = new Date(earthquake.getTime());

        // Set the date
        TextView dateTextView = listItemView.findViewById(R.id.date_textview);
        dateTextView.setText(dateFormat.format(date));

        // Set the time
        TextView timeTextView = listItemView.findViewById(R.id.time_textview);
        timeTextView.setText(timeFormat.format(date));

        return listItemView;
    }


    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
