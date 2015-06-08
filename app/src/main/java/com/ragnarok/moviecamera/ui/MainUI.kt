package com.ragnarok.moviecamera.ui

import android.os.Bundle
import com.ragnarok.moviecamera.R

/**
 * Created by ragnarok on 15/6/9.
 */

class MainUI: BaseUI() {
    
    override val TAG: String = "MovieCamera.MainUI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}