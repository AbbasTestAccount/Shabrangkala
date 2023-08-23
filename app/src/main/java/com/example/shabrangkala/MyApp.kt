package com.example.shabrangkala

import android.app.Application
import com.example.shabrangkala.di.myModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin(appDeclaration = {
            androidContext(this@MyApp)
            modules(myModules)
        })
    }
}