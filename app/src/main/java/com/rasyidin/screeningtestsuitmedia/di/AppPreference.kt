package com.rasyidin.screeningtestsuitmedia.di

import android.content.Context
import com.rasyidin.screeningtestsuitmedia.utils.Constants.EVENT_PREF
import com.rasyidin.screeningtestsuitmedia.utils.Constants.NAME_PREF
import com.rasyidin.screeningtestsuitmedia.utils.Constants.PREF_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreference @Inject constructor(@ApplicationContext context: Context) {

    private val pref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)

    fun saveNamePref(name: String) {
        val editor = pref.edit()
        editor.putString(NAME_PREF, name)
        editor.apply()
    }

    fun saveEventPref(name: String) {
        val editor = pref.edit()
        editor.putString(EVENT_PREF, name)
        editor.apply()
    }

    fun removeEventPref() {
        val editor = pref.edit()
        editor.remove(EVENT_PREF)
        editor.apply()
    }

    fun getNamePref() = pref.getString(NAME_PREF, "")

    fun getEventPref() = pref.getString(EVENT_PREF, null)
}