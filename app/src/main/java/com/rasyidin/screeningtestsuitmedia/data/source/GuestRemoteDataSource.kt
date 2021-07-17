package com.rasyidin.screeningtestsuitmedia.data.source

import com.rasyidin.screeningtestsuitmedia.data.utils.JsonHelper
import javax.inject.Inject

class GuestRemoteDataSource @Inject constructor(private val jsonHelper: JsonHelper) {

    fun getGuest() = jsonHelper.getGuest()

    fun getEvent() = jsonHelper.getEvents()
}