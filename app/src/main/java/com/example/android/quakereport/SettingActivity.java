package com.example.android.quakereport;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SeekBarPreference;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }

    public static class EarthquakePreferenceFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.setting_main, rootKey);

            EditTextPreference limit = findPreference(getString(R.string.settings_limit_key));
            limit.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
            });
            limit.setSummaryProvider(EditTextPreference.SimpleSummaryProvider.getInstance());
//            bindPreferenceSummaryToValue(minMagnitude);

            ListPreference orderBy = findPreference(getString(R.string.settings_order_by_key));
            orderBy.setSummaryProvider(ListPreference.SimpleSummaryProvider.getInstance());
//            bindPreferenceSummaryToValue(orderBy);

            EditTextPreference min_magnitude = findPreference(getString(R.string.settings_min_magnitude_key));
            min_magnitude.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                }
            });
            min_magnitude.setSummaryProvider(EditTextPreference.SimpleSummaryProvider.getInstance());
        }

//    private void bindPreferenceSummaryToValue(Preference preference) {
//        preference.setOnPreferenceChangeListener(this);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
//        String preferenceString = preferences.getString(preference.getKey(), "");
//        onPreferenceChange(preference, preferenceString);
//    }


        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            return false;
        }

    }
}
