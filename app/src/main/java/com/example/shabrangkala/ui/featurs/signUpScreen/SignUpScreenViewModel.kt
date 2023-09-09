package com.example.shabrangkala.ui.featurs.signUpScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpScreenViewModel : ViewModel() {
    val userName = MutableLiveData("")
    val email = MutableLiveData("")
    val phoneNumber = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")
}