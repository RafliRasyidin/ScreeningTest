package com.rasyidin.screeningtestsuitmedia.data.source.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rasyidin.screeningtestsuitmedia.data.model.Guest

@Dao
interface GuestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGuest(listGuest: List<Guest>)

    @Query("SELECT * FROM guest")
    suspend fun getAllGuest(): List<Guest>
}