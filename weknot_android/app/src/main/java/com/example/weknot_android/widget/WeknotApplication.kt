package com.example.weknot_android.widget

import android.app.Application

class WeknotApplication : Application() {

    private var TAG = WeknotApplication::class.java.simpleName
    private var androidDefaultUEH: Thread.UncaughtExceptionHandler? = null
    private var uncaughtExceptionHandler: UncaughtExceptionHandler? = null

    fun getUncaughtExceptionHandler(): UncaughtExceptionHandler? {
        return uncaughtExceptionHandler
    }

    override fun onCreate() {
        androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler()
//        uncaughtExceptionHandler = UncaughtExceptionHandler()

        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler)
        super.onCreate()
    }
}