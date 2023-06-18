package com.example.githubsearch

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.hide(){
    this.visibility = View.GONE
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun ImageView.loadCircleImage(url: String, placeholder: Drawable? = null){
    Glide.with(this.context)
        .load(url)
        .placeholder(placeholder)
        .circleCrop()
        .into(this)
}

