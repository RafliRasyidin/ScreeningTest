package com.rasyidin.screeningtestsuitmedia.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    fun isPalindrome(name: String) : Boolean {
        val nameWithoutSpace = name.replace("\\s".toRegex(), "")
        val stringBuilder = StringBuilder(nameWithoutSpace)
        val reverseString = stringBuilder.reverse().toString()
        return nameWithoutSpace.equals(reverseString, true)
    }
}