package com.sophia1.colorappnuevo;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Pref extends PreferenceFragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
