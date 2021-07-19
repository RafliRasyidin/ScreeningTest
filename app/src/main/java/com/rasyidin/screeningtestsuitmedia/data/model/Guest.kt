package com.rasyidin.screeningtestsuitmedia.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "guest")
@Parcelize
data class Guest(
    @PrimaryKey
    val id: Int,
    val name: String,
    val birthdate: String,
    val image: String
) : Parcelable
