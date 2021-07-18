package com.rasyidin.screeningtestsuitmedia.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.databinding.FragmentHomeBinding
import com.rasyidin.screeningtestsuitmedia.di.AppPreference
import com.rasyidin.screeningtestsuitmedia.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    @Inject
    lateinit var pref: AppPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isEmpty()) {
                binding.etName.error = getString(R.string.name_error)
            } else {
                binding.etName.error = null
                pref.saveNamePref(name)
                findNavController().navigate(R.id.action_homeFragment_to_welcomeFragment)
            }
        }
    }
}