package com.ragnarok.moviecamera.util

import android.util.Log
import java.lang

/**
 * Created by ragnarok on 15/6/9.
 */

object CamLogger {
    fun d(TAG: String, format: String, vararg args: Any) {
        if (args.size() > 0) {
            Log.d(TAG, lang.String.format(format, args))
        } else {
            Log.d(TAG, format)
        }
    }

    fun v(TAG: String, format: String, vararg args: Any) {
        if (args.size() > 0) {
            Log.v(TAG, lang.String.format(format, args))
        } else {
            Log.v(TAG, format)
        }
    }

    fun w(TAG: String, format: String, vararg args: Any) {
        if (args.size() > 0) {
            Log.w(TAG, lang.String.format(format, args))
        } else {
            Log.w(TAG, format)
        }
    }

    fun e(TAG: String, format: String, vararg args: Any) {
        if (args.size() > 0) {
            Log.e(TAG, lang.String.format(format, args))
        } else {
            Log.e(TAG, format)
        }
    }
}