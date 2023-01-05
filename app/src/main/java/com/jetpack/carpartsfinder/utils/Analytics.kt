package com.jetpack.carpartsfinder.utils

import android.content.Context
import android.util.Log
import com.appsflyer.AFInAppEventParameterName
import com.appsflyer.AFInAppEventType
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener

object Analytics {
    const val SCREEN_PARTS = "parts_list_screen"
    const val EVENT_CARD_CLICK = "card_click"

    private const val TAG = "analytics"
    private var isInit = false

    fun init(key: String?, context: Context) {
        if (isInit) {
            return
        }
        if (key == null) {
            Log.i(TAG, "Empty key. Appslfyer not initialized")
            return
        }

        val appsflyer = AppsFlyerLib.getInstance()
        // For debug - remove in production
        appsflyer.setDebugLog(true)
        //optional
//            appsflyer.setMinTimeBetweenSessions(0)
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
            return
        }

        val params = mutableMapOf<String, Any?>(
            AFInAppEventParameterName.CONTENT_ID to screenName,
            AFInAppEventParameterName.CONTENT_TYPE to eventName,
//                AFInAppEventParameterName.CUSTOMER_USER_ID to 567,
        )
//        if (flowName != null) {
//            params[AFConstant.PAR_CONTENT_TYPE] = flowName
//        }
        AppsFlyerLib.getInstance().logEvent(
            context,
            AFInAppEventType.CONTENT_VIEW,
            params,
//                object : AppsFlyerRequestListener {
//                    override fun onSuccess() {
//                        Log.d("!!!!!!!!!!!!!!!!!!!!!!", "Event sent successfully")
//                    }
//
//                    override fun onError(errorCode: Int, errorDesc: String) {
//                        Log.d(
//                            "!!!!!!!!!!!!!!!!!!!!!", "Event failed to be sent:\n" +
//                                "Error code: " + errorCode + "\n"
//                                + "Error description: " + errorDesc
//                        )
//                    }
//                }
        )

    }
}
