package com.example.android.quakereport;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private String mUrl;
    private static final String LOG_TAG = "QuakeReport";
    Context mContext;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
        mContext = context;
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        Log.d(LOG_TAG, "TEST: запуск hhtp-соединения в loadInBackground");
        if (mUrl == null) return null;
        List<Earthquake> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG, "TEST: Loader запущен в onStartLoading");
        forceLoad();
    }
}

