package com.example.shabrangkala.ui.featurs.logInScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogInScreenViewModel : ViewModel() {
    val userName = MutableLiveData("")
    val password = MutableLiveData("")
}