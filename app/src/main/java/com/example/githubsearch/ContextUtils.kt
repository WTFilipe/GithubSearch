package com.example.githubsearch

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat


@SuppressLint("ResourceAsColor")
fun Context.getTintedDrawable(drawable: Int): Drawable? {

    val isNightMode: Boolean = (this.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    val color = if (isNightMode) {
        R.color.white
    } else {
        R.color.black
    }

    val unwrappedDrawable = ContextCompat.getDrawable(this, drawable)
    val wrappedDrawable = unwrappedDrawable?.let { DrawableCompat.wrap(it) }
    if (wrappedDrawable != null) {
        DrawableCompat.setTint(wrappedDrawable, color)
    }

    return wrappedDrawable
}

