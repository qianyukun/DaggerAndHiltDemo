package com.qyk.hiltdemo.application

import android.app.Application
import com.qyk.hiltdemo.analytics.AnalyticManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HiltApplication : Application() {
    @Inject
    lateinit var analytics: AnalyticManager

    override fun onCreate() {
        super.onCreate()
        analytics.init(this)
        analytics.trackEvent("appopen")
    }
}