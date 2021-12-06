package com.qyk.daggerdemo

import android.app.Application

lateinit var mainComponent: MainComponent

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder().build()
    }
}