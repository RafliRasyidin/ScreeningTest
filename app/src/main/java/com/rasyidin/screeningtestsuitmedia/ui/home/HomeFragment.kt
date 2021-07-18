package com.rasyidin.screeningtestsuitmedia.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.databinding.FragmentHomeBinding
import com.rasyidin.screeningtestsuitmedia.di.AppPreference
import com.rasyidin.screeningtestsuitmedia.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var pref: AppPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDone.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isEmpty()) {
                binding.etName.error = getString(R.string.name_error)
            } else {
                binding.etName.error = null
                determinePalindrome(homeViewModel.isPalindrome(name))
                pref.saveNamePref(name)
                findNavController().navigate(R.id.action_homeFragment_to_welcomeFragment)
            }
        }
    }

    private fun determinePalindrome(isPalindrome: Boolean) {
        if (isPalindrome) {
            Toast.makeText(
                requireActivity(),
                getString(R.string.is_palindrome),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                requireActivity(),
                getString(R.string.not_palindrome),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}