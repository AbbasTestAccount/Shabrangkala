package com.example.shabrangkala.ui.featurs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogInScreenViewModel : ViewModel() {
    val userName = MutableLiveData("")
    val password = MutableLiveData("")
}