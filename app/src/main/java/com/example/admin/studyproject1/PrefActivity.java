package com.example.admin.studyproject1;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by admin on 2017-07-07.
 */

public class PrefActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_layout);
    }
}
