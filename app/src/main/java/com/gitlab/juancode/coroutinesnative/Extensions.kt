package com.gitlab.juancode.coroutinesnative

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url : String) {
    Glide.with(this).load(url).into(this)
}