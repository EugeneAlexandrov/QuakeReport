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

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        final ArrayList<Event> earthquakes = new ArrayList<Event>();
        earthquakes.add(new Event("Feb 2, 2016", 7.2f, "San Francisco"));
        earthquakes.add(new Event("July 20, 2015", 6.1f, "London"));
        earthquakes.add(new Event("Nov 10, 2014", 3.9f, "Tokyo"));
        earthquakes.add(new Event("May 3, 2014", 5.4f, "Mexico City"));
        earthquakes.add(new Event("Jan 31, 2013", 2.8f, "Moscow"));
        earthquakes.add(new Event("Aug 19, 2012", 4.9f, "Rio de Janeiro"));
        earthquakes.add(new Event("Oct 30, 2011", 1.6f, "Paris"));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EventAdapter adapter = new EventAdapter(
                this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
