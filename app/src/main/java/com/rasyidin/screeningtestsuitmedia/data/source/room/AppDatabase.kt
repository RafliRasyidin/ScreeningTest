package com.rasyidin.screeningtestsuitmedia.data.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rasyidin.screeningtestsuitmedia.data.model.Guest

@Database(entities = [Guest::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getGuestDao() : GuestDao
}