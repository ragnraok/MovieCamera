package com.ragnarok.moviecamera

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.support.v7.app.ActionBarActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.TextView
android.support.v7.widget.Toolbar

/**
 * Created by ragnarok on 15/6/8.
 */

public abstract class BaseUI: ActionBarActivity() {
    val TAG: String = "MovieCamera.BaseUI"
    
    protected var mToolbar: Toolbar? = null
    protected var mTitleText: TextView? = null
    
    private var mUIHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
    }
    
    private fun initToolbar() {
        try {
            mToolbar = findViewById(R.id.toolbar) as Toolbar
            setSupportActionBar(mToolbar)
            
            mTitleText = mToolbar?.findViewById(R.id.title) as TextView
            mTitleText?.setTypeface(Typeface.createFromAsset("appleberry.ttf"))
            
        } catch (e: Exception) {
            Log.e(TAG, "initToolbar error: " + e.getMessage())
        }
        
    }
    
    protected fun getToolbar(): Toolbar? {
        return mToolbar
    }
    
    protected fun getTitleText(): TextView? {
        return mTitleText
    }
    
    private fun startToobarInitAnim() {
        mUIHandler.post { startToolbarInitAnimInternal() }   
    }
    
    val TOOLBAR_INIT_ANIM_DURATION = 300
    val TITLE_TEXT_INIT_ANIM_DURATION = 350
    
    private fun startToolbarInitAnimInternal() {
        
    }
}