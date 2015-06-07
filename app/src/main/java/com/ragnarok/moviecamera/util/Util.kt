package com.ragnarok.moviecamera.util

import android.content.Context
import android.util.TypedValue

/**
 * Created by ragnarok on 15/6/8.
 */

object ScreenSize {
    var screenWidth = 0
    var screenHeight = 0
}

public fun getActionBarHeight(context: Context): Int {
    val tv = TypedValue()
    if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
        return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics())
    }
    return 0
}