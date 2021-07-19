package com.rasyidin.screeningtestsuitmedia.data.source

import com.rasyidin.screeningtestsuitmedia.data.model.Guest
import com.rasyidin.screeningtestsuitmedia.data.source.room.GuestDao
import javax.inject.Inject

class GuestLocalDataSource @Inject constructor(private val guestDao: GuestDao){

    suspend fun saveGuest(listGuest: List<Guest>) = guestDao.saveGuest(listGuest)

    suspend fun getAllGuest() = guestDao.getAllGuest()
}