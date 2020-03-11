package com.example.android.quakereport;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private String mUrl;
    private static final String LOG_TAG = "QuakeReport";

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        Log.d(LOG_TAG,"TEST: loadInBackground");
        if (mUrl==null) return null;
        List<Earthquake> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG,"TEST: onStartLoading");
        forceLoad();
    }
}

