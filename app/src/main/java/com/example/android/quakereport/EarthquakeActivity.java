/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.PreferenceManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>>, SwipeRefreshLayout.OnRefreshListener {

    private static final String LOG_TAG = "QuakeReport";
    private static final String USGS_RESPONSE = "https://earthquake.usgs.gov/fdsnws/event/1/query";
    private static final int LOADER_ID = 1;
    private EventAdapter mAdapter;
    public TextView emptystate;
    public ProgressBar progress;
    private Context context;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "TEST: onDestroy вызван");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "TEST: onCreate вызван");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        progress = (ProgressBar) findViewById(R.id.progress);
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        emptystate = (TextView) findViewById(R.id.empty_state);
        earthquakeListView.setEmptyView(emptystate);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EventAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri address = Uri.parse(mAdapter.getItem(position).getmURL());
                Intent intent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(intent);
            }
        });
        // Start the AsyncTask to fetch the earthquake data
        Log.d(LOG_TAG, "TEST: вызов или создание нового Loader");
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            progress.setVisibility(View.GONE);
            emptystate.setText("No internet connection");
        }
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "TEST: onStart вызван");
    }

    @NonNull
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d(LOG_TAG, "TEST: создание Loader");
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String limit = sharedPrefs.getString(
                getString(R.string.settings_limit_key),
                getString(R.string.settings_limit_default));
        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));
        String min_magnitude = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));
        Uri baseUri = Uri.parse(USGS_RESPONSE);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", limit);
        uriBuilder.appendQueryParameter("minmagnitude", min_magnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);

        return new EarthquakeLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Earthquake>> loader, List<Earthquake> data) {
        // Clear the adapter of previous earthquake data
        Log.d(LOG_TAG, "TEST: Loader закончил загрузку");
        emptystate.setText(R.string.no_earthquakes);
        progress.setVisibility(View.GONE);
        mAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Earthquake>> loader) {
        Log.d(LOG_TAG, "TEST: Loader прервал загрузку");
        mAdapter.clear();
    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting) {
            Intent settingsIntent = new Intent(this, SettingActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
