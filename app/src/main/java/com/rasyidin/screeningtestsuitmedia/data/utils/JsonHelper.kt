package com.rasyidin.screeningtestsuitmedia.data.utils

import android.content.Context
import com.rasyidin.screeningtestsuitmedia.data.model.Event
import com.rasyidin.screeningtestsuitmedia.data.model.Guest
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(filename: String): String? {
        return try {
            val `is` = context.assets.open(filename)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun getGuest(): List<Guest> {
        val listGuest = ArrayList<Guest>()
        try {
            val listArray = JSONArray(parsingFileToString("guest.json"))
            for (i in 0 until listArray.length()) {
                val guest = listArray.getJSONObject(i)

                val id = guest.getInt("id")
                val name = guest.getString("name")
                val birthdate = guest.getString("birthdate")
                val image = guest.getString("image")

                val guestResponse = Guest(id, name, birthdate, image)

                listGuest.add(guestResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return listGuest
    }

    fun getEvents(): List<Event> {
        val listEvent = ArrayList<Event>()
        try {
            val listArray = JSONArray(parsingFileToString("event.json"))
            for (i in 0 until listArray.length()) {
                val event = listArray.getJSONObject(i)
                val id = event.getInt("id")
                val name = event.getString("name")
                val date = event.getString("date")
                val hashtag = ArrayList<String>()
                val listHashtag = event.getJSONArray("hashtag")
                for (index in 0 until listHashtag.length()) {
                    hashtag.add(listHashtag.getString(index))
                }
                val image = event.getString("image")
                val imageEvent = event.getString("imageEvent")
                val lat = event.getDouble("lat")
                val lng = event.getDouble("lng")

                val eventResponse = Event(id, name, date, hashtag, image, imageEvent, lat, lng)
                listEvent.add(eventResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return listEvent
    }
}