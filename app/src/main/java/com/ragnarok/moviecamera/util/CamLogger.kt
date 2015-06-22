package com.ragnarok.moviecamera.util

import android.util.Log
import java.lang

/**
 * Created by ragnarok on 15/6/9.
 */

object CamLogger {
    fun d(TAG: String, content: String) {
        Log.d(TAG, content)
    }

    fun v(TAG: String, content: String) {
        Log.v(TAG, content)
    }

    fun w(TAG: String, content: String) {
       Log.w(TAG, content)
    }

    fun e(TAG: String, content: String) {
        Log.e(TAG, content)
    }
}