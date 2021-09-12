package com.dicoding.academy.githubuser.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.dicoding.academy.githubuser.R

class SettingsPreference: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference, rootKey)
    }
}