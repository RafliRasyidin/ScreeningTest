package com.rasyidin.screeningtestsuitmedia.ui.guest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.data.model.Guest
import com.rasyidin.screeningtestsuitmedia.databinding.FragmentGuestBinding
import com.rasyidin.screeningtestsuitmedia.ui.adapter.GuestAdapter
import com.rasyidin.screeningtestsuitmedia.ui.base.BaseFragment
import com.rasyidin.screeningtestsuitmedia.utils.getBirthdate
import com.rasyidin.screeningtestsuitmedia.utils.getMonth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuestFragment : BaseFragment<FragmentGuestBinding>(FragmentGuestBinding::inflate), SwipeRefreshLayout.OnRefreshListener {

    private val guestViewModel: GuestViewModel by viewModels()

    private lateinit var guest: List<Guest>

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

    override fun onRefresh() {
        binding.swipeRefresh.isRefreshing = false
        guestViewModel.saveGuest(guest)
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launchWhenCreated {
            guestViewModel.getAllGuest()
            guestViewModel.guestList.observe(viewLifecycleOwner) { listGuest ->
                guestAdapter.setData(listGuest)
            }
        }
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
            guest = listGuest
        }
    }

    private fun setupAdapter() = binding.rvGuest.apply {
        adapter = guestAdapter
        layoutManager = GridLayoutManager(requireActivity(), 2)
        setHasFixedSize(true)

        binding.swipeRefresh.apply {
            setOnRefreshListener(this@GuestFragment)
            setColorSchemeResources(
                R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark,
            )
        }
    }

    private fun isMonthPrime(isPrime: Boolean) {
        if (isPrime) {
            Snackbar.make(requireView(), getString(R.string.is_prime), Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(requireView(), getString(R.string.not_prime), Snackbar.LENGTH_SHORT).show()
        }
    }


}