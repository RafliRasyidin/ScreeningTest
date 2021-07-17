package com.rasyidin.screeningtestsuitmedia.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.databinding.FragmentWelcomeBinding
import com.rasyidin.screeningtestsuitmedia.di.AppPreference
import com.rasyidin.screeningtestsuitmedia.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {

    @Inject
    lateinit var pref: AppPreference

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvUsername.text = ": ${pref.getNamePref()}"
        val isPrefEventNull = pref.getEventPref() == null

        if (isPrefEventNull) {
            binding.btnEvent.text = getString(R.string.action_event)
        } else {
            binding.btnEvent.text = pref.getEventPref()
        }

        binding.btnGuest.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_guestFragment)
        }

        binding.btnEvent.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_eventFragment)
        }
    }

}