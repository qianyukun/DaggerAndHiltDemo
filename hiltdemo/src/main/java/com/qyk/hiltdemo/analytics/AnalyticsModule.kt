package com.qyk.hiltdemo.analytics

import android.content.Context
import com.qyk.domain.AnalyticService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Singleton
    @Provides
    @GoogleAnalytics
    fun providerGoogleAnalyticsService(): AnalyticService {
        return object : AnalyticService {

            override fun init(context: Context) {
                android.util.Log.e("xxxx", "google analytics $context init")

            }

            override fun trackEvent(event: String, extra: Map<String, Any>?) {
                android.util.Log.e("xxxx", "google analytics $event $extra")
            }
        }
    }

    @Singleton
    @Provides
    @FacebookAnalytics
    fun providerFacebookAnalyticsService(): AnalyticService {
        return object : AnalyticService {

            override fun init(context: Context) {
                android.util.Log.e("xxxx", "facebook analytics $context init")

            }

            override fun trackEvent(event: String, extra: Map<String, Any>?) {
                android.util.Log.e("xxxx", "facebook analytics $event $extra")

            }
        }
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GoogleAnalytics()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FacebookAnalytics()