package com.dertyp7214.libraries.logs

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit

class Logger(private val activity: Activity) {

    private val sharedPreferences: SharedPreferences = activity.getSharedPreferences("logger", Context.MODE_PRIVATE)

    fun logD(tag: String, exception: Exception) {
        Log.d(tag, exception.toString())
    }

    fun logD(tag: String, message: String) {
        Log.d(tag, message)
    }

    var saveLogs: Boolean
        get() {
            return sharedPreferences.getBoolean("saveLogs", false)
        }
        set(value) {
            sharedPreferences.edit {
                putBoolean("saveLogs", value)
            }
        }

}