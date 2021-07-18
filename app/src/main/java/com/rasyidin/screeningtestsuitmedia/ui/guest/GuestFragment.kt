package com.rasyidin.screeningtestsuitmedia.ui.guest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.databinding.FragmentGuestBinding
import com.rasyidin.screeningtestsuitmedia.ui.adapter.GuestAdapter
import com.rasyidin.screeningtestsuitmedia.ui.base.BaseFragment
import com.rasyidin.screeningtestsuitmedia.utils.getBirthdate
import com.rasyidin.screeningtestsuitmedia.utils.getMonth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuestFragment : BaseFragment<FragmentGuestBinding>(FragmentGuestBinding::inflate) {

    private val guestViewModel: GuestViewModel by viewModels()

    private val guestAdapter: GuestAdapter by lazy {
        GuestAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarContainer.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = null
        binding.toolbarContainer.tvToolbar.text = getString(R.string.guest)
        setupAdapter()

        subscribeToObserver()

        onItemClicked()
    }

    private fun onItemClicked() {
        guestAdapter.onItemClickListener = { guest ->
            val toastMessage = guestViewModel.determineMultiplesOfBirthdate(guest.birthdate.getBirthdate(), requireActivity())
            val isPrime = guestViewModel.isMonthPrime(guest.birthdate.getMonth())
            isMonthPrime(isPrime)
            Toast.makeText(requireActivity(), toastMessage, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_guestFragment_to_welcomeFragment)
        }
    }

    private fun subscribeToObserver() {
        guestViewModel.getListGuest().observe(viewLifecycleOwner) { listGuest ->
            guestAdapter.setData(listGuest)
        }
    }

    private fun setupAdapter() = binding.rvGuest.apply {
        adapter = guestAdapter
        layoutManager = GridLayoutManager(requireActivity(), 2)
        setHasFixedSize(true)
    }

    private fun isMonthPrime(isPrime: Boolean) {
        if (isPrime) {
            Snackbar.make(requireView(), getString(R.string.is_prime), Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(requireView(), getString(R.string.not_prime), Snackbar.LENGTH_SHORT).show()
        }
    }
}