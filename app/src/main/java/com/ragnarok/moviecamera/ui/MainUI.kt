package com.ragnarok.moviecamera.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.ragnarok.moviecamera.R

/**
 * Created by ragnarok on 15/6/9.
 */

class MainUI: BaseUI() {
    
    override val TAG: String = "MovieCamera.MainUI"
    
    var mImageList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        mImageList = findViewById(R.id.images_list) as RecyclerView
    }
}