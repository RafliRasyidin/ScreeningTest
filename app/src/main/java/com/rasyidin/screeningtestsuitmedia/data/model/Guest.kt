package com.rasyidin.screeningtestsuitmedia.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Guest(
    val id: Int,
    val name: String,
    val birthdate: String,
    val image: String
) : Parcelable
