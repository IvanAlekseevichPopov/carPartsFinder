package com.jetpack.carpartsfinder.util

import android.content.Context
import android.util.Log
import com.appsflyer.AFInAppEventParameterName
import com.appsflyer.AFInAppEventType
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.jetpack.carpartsfinder.R

object Analytics { //TODO inject context by hilt
    const val SCREEN_PARTS = "parts_list_screen"
    const val EVENT_CARD_CLICK = "card_click"

    private const val TAG = "analytics"
    private var isInit = false

    fun init(context: Context) {
        if (isInit) {
            return
        }
        val key: String = context.resources.getString(R.string.appsflyerDevKey)
        if(key.isEmpty()) {
            Log.i(TAG, "Empty key. Appslfyer not initialized")
            return
        }

        val appsflyer = AppsFlyerLib.getInstance()
        appsflyer.init(key, null, context)
        appsflyer.start(context, key, object : AppsFlyerRequestListener {
            override fun onSuccess() {
                isInit = true
                Log.d(TAG, "Launch sent successfully, got 200 response code from server")
            }

            override fun onError(i: Int, s: String) {
                Log.w(
                    TAG, "Launch failed to be sent:\n" +
                        "Error code: " + i + "\n"
                        + "Error description: " + s
                )
            }
        })
    }

    fun event(context: Context, screenName: String, eventName: String) {
        //TODO enum for screen name and event name
        if (!isInit) {
            Log.d(TAG, "Appsflyer not initialized. Event won't be sent")

            return
        }

        val params = mutableMapOf<String, Any?>(
            AFInAppEventParameterName.CONTENT_ID to screenName,
            AFInAppEventParameterName.CONTENT_TYPE to eventName,
        )
        AppsFlyerLib.getInstance().logEvent(
            context,
            AFInAppEventType.CONTENT_VIEW,
            params
        )

    }
}
