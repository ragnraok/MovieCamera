package com.ragnarok.moviecamera.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ragnarok.moviecamera.R

/**
 * Created by ragnarok on 15/6/9.
 */

class MainUI: BaseUI() {
    
    override val TAG: String = "MovieCamera.MainUI"
    
    var imageList: RecyclerView? = null
    var imageListAdapter: ImageListAdapter? = null
    var imageLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
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
    }
}