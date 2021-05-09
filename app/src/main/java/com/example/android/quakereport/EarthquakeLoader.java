package com.example.android.quakereport;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    // Query URL
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * Load data from server on a background thread
     */
    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mUrl == null) {
            return null;
        }

        return QueryUtils.fetchEarthquakesData(mUrl);
    }
}
