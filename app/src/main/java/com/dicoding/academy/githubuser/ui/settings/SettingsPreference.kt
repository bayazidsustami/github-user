package com.dicoding.academy.githubuser.ui.settings

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.academy.githubuser.R
import com.dicoding.academy.githubuser.utility.DailyNotification
import com.dicoding.academy.githubuser.utility.DailyNotification.Companion.NOTIFICATION_ID
import java.util.*

class SettingsPreference : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener,
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var switchPreference: SwitchPreference
    private lateinit var alarmManager: AlarmManager
    private lateinit var alarmIntent: Intent

    companion object {
        private const val DAILY_KEY = "reminder_switch"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference, rootKey)

        init()
    }

    private fun init() {
        switchPreference = findPreference<SwitchPreference>(DAILY_KEY) as SwitchPreference
        switchPreference.onPreferenceChangeListener = this

        alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(requireContext(), DailyNotification::class.java)
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        val key = preference?.key
        val value = newValue as Boolean

        if (key == DAILY_KEY) {
            if (value) {
                setRepeatingAlarm()
            } else {
                cancelAlarm()
            }
        }

        return true
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == DAILY_KEY) {
            switchPreference.isChecked = sharedPreferences?.getBoolean(DAILY_KEY, false) as Boolean
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun setRepeatingAlarm() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        val pendingIntent =
            PendingIntent.getBroadcast(requireContext(), NOTIFICATION_ID, alarmIntent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        showToast("Alarm is set!")
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun cancelAlarm() {
        val pendingIntent =
            PendingIntent.getBroadcast(requireContext(), NOTIFICATION_ID, alarmIntent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        showToast("Alarm Canceled")

    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}