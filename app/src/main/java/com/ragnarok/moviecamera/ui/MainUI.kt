package com.ragnarok.moviecamera.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageButton
import com.ragnarok.moviecamera.R

/**
 * Created by ragnarok on 15/6/9.
 */

class MainUI : BaseUI() {

    override val TAG: String = "MovieCamera.MainUI"

    var imageList: RecyclerView? = null
    var imageListAdapter: ImageListAdapter? = null
    var imageLayoutManager: LinearLayoutManager? = null
    var createButton: ImageButton? = null
    var revealMaskView: CircularRevealMaskView? = null
    
    val uiHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createButton = findViewById(R.id.create) as ImageButton
        revealMaskView = findViewById(R.id.revealView) as CircularRevealMaskView
        revealMaskView?.setColor(0xAFF0F8FF.toInt())

        val createButtonTransY = getResources().getDimension(R.dimen.fab_size) + getResources().getDimension(R.dimen.fab_bottom_margin) + 100
        createButton?.setTranslationY(createButtonTransY)

        createButton?.setOnClickListener { v: View ->
            var location = Array(2, { 0 }).toIntArray()
            createButton?.getLocationOnScreen(location)
            val x = location[0] + createButton!!.getWidth() / 2
            val y = location[1] + createButton!!.getHeight() / 2
            showRevealView(x, y)
            
        }

        initImageList()
    }
    
    private fun showRevealView(locX: Int, locY: Int) {
        uiHandler.postDelayed({revealMaskView?.startShow(locX, locY)}, 50)
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