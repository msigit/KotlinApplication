package com.mcc.kotlinapplication.app

import android.app.Application
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

       Stetho.initializeWithDefaults(this)
    }

}