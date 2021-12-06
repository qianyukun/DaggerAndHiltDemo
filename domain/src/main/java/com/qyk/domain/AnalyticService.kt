package com.qyk.domain

import android.content.Context

interface AnalyticService {
    fun init(context: Context)

    fun trackEvent(event: String, extra: Map<String, Any>? = null)
}
