package com.example.karma.recursos

import android.content.Context
import android.content.SharedPreferences

class PreferenceProvider {

    companion object {
        lateinit var preference: SharedPreferences
        fun initialize(context: Context) {
            preference = context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE)
        }

        fun setValue(value: Boolean) {
            preference.edit().putBoolean("logged", value).apply()
        }


        fun getValue(): Boolean? {
            return preference.getBoolean("logged", false)
        }


    }


}