package com.hamzaaktay.kotlincountries.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import kotlinx.coroutines.internal.synchronized

class CustomSharedPreferences {

    companion object {
        private val PREFERENCES_TIME = "preferences time"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instace : CustomSharedPreferences? = null
        private val lock = Any()

        operator fun invoke (context: Context) : CustomSharedPreferences = instace?: kotlin.synchronized(
            lock) {
            instace ?: makeCustomSharedPreferences(context).also {
                instace = it
            }
        }

        private fun makeCustomSharedPreferences (context: Context) : CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveTime (time: Long) {
        sharedPreferences?.edit(commit = true) {
            putLong(PREFERENCES_TIME,time)
        }
    }

    fun getTime () = sharedPreferences?.getLong(PREFERENCES_TIME,0)
}