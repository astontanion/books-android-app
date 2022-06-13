package com.example.books.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.books.R


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    view.load(imageUrl) {
        placeholder(R.drawable.ic_launcher_foreground)
        error(R.drawable.ic_launcher_background)
    }
}