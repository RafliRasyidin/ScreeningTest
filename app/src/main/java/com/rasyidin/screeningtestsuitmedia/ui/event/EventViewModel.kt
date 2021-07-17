package com.rasyidin.screeningtestsuitmedia.ui.event

import androidx.lifecycle.ViewModel
import com.rasyidin.screeningtestsuitmedia.data.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    fun getEvents() = appRepository.getEvents()
}