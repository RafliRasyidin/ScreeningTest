package com.rasyidin.screeningtestsuitmedia.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: Int,
    val name: String,
    val date: String,
    val image: String
) : Parcelable
