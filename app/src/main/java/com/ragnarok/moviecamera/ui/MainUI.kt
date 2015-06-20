package com.ragnarok.moviecamera.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageButton
import com.ragnarok.moviecamera.R

/**
 * Created by ragnarok on 15/6/9.
 */

class MainUI: BaseUI() {
    
    override val TAG: String = "MovieCamera.MainUI"
    
    var imageList: RecyclerView? = null
    var imageListAdapter: ImageListAdapter? = null
    var imageLayoutManager: LinearLayoutManager? = null
    var createButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        createButton = findViewById(R.id.create) as ImageButton
        
        val createButtonTransY = getResources().getDimension(R.dimen.fab_size) + getResources().getDimension(R.dimen.fab_bottom_margin) + 100
        createButton?.setTranslationY(createButtonTransY)
        
        initImageList()
    }
    
    private fun initImageList() {
        imageList = findViewById(R.id.images_list) as RecyclerView
        imageListAdapter = ImageListAdapter(this)
        
        imageLayoutManager = LinearLayoutManager(this)
        imageList?.setLayoutManager(imageLayoutManager)
        imageList?.setAdapter(imageListAdapter)
    }

    override fun onToolbarInitAnimFinish() {
        imageListAdapter?.updateItems()
        
        createButton?.animate()!!.translationY(0f).setInterpolator(OvershootInterpolator()).setDuration(300).setStartDelay(250)
    }
}