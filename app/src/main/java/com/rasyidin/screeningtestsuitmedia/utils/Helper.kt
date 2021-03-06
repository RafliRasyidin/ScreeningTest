package com.rasyidin.screeningtestsuitmedia.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

fun ImageView.setImage(context: Context, image: String) {
    Glide.with(context)
        .load(context.resources.getIdentifier(
            image,
            "drawable",
            context.packageName
        ))
        .into(this)
}

fun ImageView.setImageWithUrl(context: Context, imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}

@SuppressLint("SimpleDateFormat")
fun String.getBirthdate(): Int {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("dd")
    val date = inputFormat.parse(this)
    return outputFormat.format(date).toInt()
}

@SuppressLint("SimpleDateFormat")
fun String.getMonth(): Int {
    val inputString = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("MM")
    val date = inputString.parse(this)
    return outputFormat.format(date).toInt()
}

