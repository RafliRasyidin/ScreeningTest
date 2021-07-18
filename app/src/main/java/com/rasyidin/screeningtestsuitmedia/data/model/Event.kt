package com.rasyidin.screeningtestsuitmedia.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: Int,
    val name: String,
    val date: String,
    val hashtag: List<String>,
    val image: String,
    val imageEvent: String,
    val lat: Double = 0.0,
    val lng: Double = 0.0
) : Parcelable
