package com.ragnarok.moviecamera.ui

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import com.ragnarok.moviecamera.R

/**
 * Created by ragnarok on 15/6/20.
 */

class ImageListAdapter(var context: MainUI): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val TAG: String = "MovieCamera.ImageListAdapter"
    
    var itemCounts = 0
    
    var lastAnimatedPosition = -1
    
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        var view = LayoutInflater.from(context).inflate(R.layout.image_item_layout, parent, false)
        val result = ImageListViewHolder(view)
        return result
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder?.getItemViewType()
        startItemInitAnim(holder?.itemView, position)
    }
    
    private fun startItemInitAnim(view: View?, position: Int) {
        if (position> lastAnimatedPosition) {
            lastAnimatedPosition = position
            
            view?.setScaleX(0.5f)
            view?.setScaleY(0.5f)
            
            if (view != null) {
                view.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(OvershootInterpolator()).setDuration(200)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemCounts;
    }
    
    public fun updateItems() {
        itemCounts = 10
        notifyDataSetChanged()
    }
    
    class ImageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnTouchListener, View.OnClickListener {
       

        var cardView: CardView? = null
        var imageView: ImageView? = null
        
        var clickLocationX = -1
        var clickLocationY = -1
        
        init {
            cardView = itemView.findViewById(R.id.image_cardview) as CardView
            imageView = itemView.findViewById(R.id.image) as ImageView
            
            cardView?.setOnTouchListener(this)
            imageView?.setOnClickListener(this)
        }

        override fun onTouch(v: View?, event: MotionEvent): Boolean {
            if (v?.getId() == R.id.image_cardview && event.getAction() == MotionEvent.ACTION_UP) {
                clickLocationX = event.getX().toInt()
                clickLocationY = event.getY().toInt()
            }
            return false;
        }

        override fun onClick(v: View) {
            if (v.getId() == R.id.image_cardview) {
                if (clickLocationX >=0 &&  clickLocationY >= 0) {
                    
                    clickLocationX = -1
                    clickLocationY = -1
                }
            }
        }
    }

}