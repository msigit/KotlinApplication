package com.mcc.kotlinapplication.utils

import android.app.Activity
import android.content.Intent
import com.mcc.kotlinapplication.activity.HomeActivity

/**
 * Created by Sahidul Islam on 6/30/2019.
 */
class ActivityUtils {

    private var sActivityUtils: ActivityUtils? = null

    fun getInstance(): ActivityUtils {
        if (sActivityUtils == null) {
            sActivityUtils = ActivityUtils()
        }
        return sActivityUtils as ActivityUtils
    }

    fun invokeActivity(activity: Activity, tClass: Class<*>, shouldFinish: Boolean) {
        val intent = Intent(activity, tClass)
        activity.startActivity(intent)
        if (shouldFinish) {
            activity.finish()
        }
    }

    fun invokeMainActivity(activity: Activity) {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
    }
}
