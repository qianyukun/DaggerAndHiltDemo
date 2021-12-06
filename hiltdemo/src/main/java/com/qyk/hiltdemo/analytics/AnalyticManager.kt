package com.qyk.hiltdemo.analytics

import android.content.Context
import com.qyk.domain.AnalyticService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticManager @Inject constructor(
    @GoogleAnalytics val googleAnalytics: AnalyticService,
    @FacebookAnalytics val facebookAnalytics: AnalyticService
) : AnalyticService {

    override fun init(context: Context) {
        android.util.Log.e("xxxx", "init analyticsServices $googleAnalytics $facebookAnalytics")
        googleAnalytics.init(context)
        facebookAnalytics.init(context)
    }

    override fun trackEvent(event: String, extra: Map<String, Any>?) {
        googleAnalytics.trackEvent(event, extra)
        facebookAnalytics.trackEvent(event, extra)
    }
}
