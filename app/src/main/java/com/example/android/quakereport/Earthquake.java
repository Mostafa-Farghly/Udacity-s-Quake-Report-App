package com.example.android.quakereport;

/**
 * {@link Earthquake} holds all the relevant information for a single earthquake
 */
public class Earthquake {

    /** Magnitude of the earthquake */
    private double mMagnitude;
    /** Place where the earthquake occurred */
    private String mPlace;
    /** Date when the earthquaked occurred */
    private long mTime;
    /** URL of the USGS web page to see additional details on the earthquake */
    private String mUrl;

     /**
     * Create a new {@link Earthquake} object.
     * @param magnitude is the magnitude of the earthquake
     * @param place is where the earthquake occurred
     * @param time  is the time in milliseconds (from the Epoch) when the earthquake happened
     */
    public Earthquake(double magnitude, String place, long time, String url) {
        mMagnitude = magnitude;
        mPlace = place;
        mTime = time;
        mUrl = url;
    }

    /**
     * Get the earthquake's magnitude.
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the earthquake's whereabouts.
     */
    public String getPlace() {
        return mPlace;
    }

    /**
     * Get the earthquake's date.
     */
    public long getTime() {
        return mTime;
    }

    /**
     * Get the URL of the USGS web page of the earthquake.
     */
    public String getUrl() {
        return mUrl;
    }
}
