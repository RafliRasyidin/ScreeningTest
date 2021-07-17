package com.rasyidin.screeningtestsuitmedia.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rasyidin.screeningtestsuitmedia.data.model.Event
import com.rasyidin.screeningtestsuitmedia.data.model.Guest
import com.rasyidin.screeningtestsuitmedia.data.source.GuestRemoteDataSource
import javax.inject.Inject

class AppRepository @Inject constructor(private val remoteDataSource: GuestRemoteDataSource) {

    fun getGuest(): LiveData<List<Guest>> {
        val guestResults = MutableLiveData<List<Guest>>()
        val guestResponse = remoteDataSource.getGuest()
        val listGuest = ArrayList<Guest>()

        guestResponse.forEach { response ->
            val guest = Guest(
                response.id,
                response.name,
                response.birthdate,
                response.image
            )
            listGuest.add(guest)
        }
        guestResults.postValue(listGuest)
        return guestResults
    }

    fun getEvents(): LiveData<List<Event>> {
        val eventResults = MutableLiveData<List<Event>>()
        val eventResponse = remoteDataSource.getEvent()
        val listEvent = ArrayList<Event>()

        eventResponse.forEach { response ->
            val event = Event(
                response.id,
                response.name,
                response.date,
                response.image
            )
            listEvent.add(event)
        }
        eventResults.postValue(listEvent)
        return eventResults
    }
}