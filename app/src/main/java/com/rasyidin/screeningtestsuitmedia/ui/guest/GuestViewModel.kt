package com.rasyidin.screeningtestsuitmedia.ui.guest

import android.content.Context
import androidx.lifecycle.ViewModel
import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.data.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuestViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    fun getListGuest() = appRepository.getGuest()

    fun determineMultiplesOfBirthdate(date: Int, context: Context): String {
        val isBirthdateMultipleOfTwo = date % 2 == 0
        val isBirthdateMultipleOfThree = date % 3 == 0
        val isBirthdateMultipleOfTwoAndThree = date % 3 == 0 && date % 2 == 0
        return when {
            isBirthdateMultipleOfTwoAndThree -> context.getString(R.string.ios)
            isBirthdateMultipleOfTwo -> context.getString(R.string.blackberry)
            isBirthdateMultipleOfThree-> context.getString(R.string.android)
            else -> context.getString(R.string.feature_phone)
        }
    }

    fun isMonthPrime(month: Int) : Boolean {
        for (i in 2..month / 2) {
            if (month % i == 0) {
                return false
            }
        }
        return true
    }
}