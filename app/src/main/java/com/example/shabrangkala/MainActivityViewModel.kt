package com.example.shabrangkala

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class MainActivityViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {


    fun checkFirstRunStatus() : Boolean{
        return sharedPreferences.getBoolean("first run", true)
    }

    fun changeFirstRunStatus() : Boolean{
        sharedPreferences.edit().putBoolean("first run", false).apply()
        return checkFirstRunStatus()
    }

    fun checkLogInStatus() : Boolean{
        return sharedPreferences.getBoolean("log in status", false)
    }

    fun changeLogInStatus() : Boolean{
        sharedPreferences.edit().putBoolean("log in status", true).apply()
        return checkLogInStatus()
    }


}