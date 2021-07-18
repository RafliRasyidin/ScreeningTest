package com.rasyidin.screeningtestsuitmedia.ui.event

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.databinding.FragmentEventBinding
import com.rasyidin.screeningtestsuitmedia.di.AppPreference
import com.rasyidin.screeningtestsuitmedia.ui.adapter.EventAdapter
import com.rasyidin.screeningtestsuitmedia.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EventFragment : BaseFragment<FragmentEventBinding>(FragmentEventBinding::inflate) {

    private val eventViewModel: EventViewModel by viewModels()

    @Inject
    lateinit var pref: AppPreference

    private val eventAdapter: EventAdapter by lazy {
        EventAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarContainer.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = null
        binding.toolbarContainer.tvToolbar.text = getString(R.string.event)

        setupAdapter()

        subscribeToObserver()

        onItemClicked()

        backButtonClicked()

        navigateToMapFragment()
    }

    private fun onItemClicked() {
        eventAdapter.onItemClickListener = { event ->
            pref.saveEventPref(event.name)
            findNavController().navigate(R.id.action_eventFragment_to_welcomeFragment)
        }
    }

    private fun backButtonClicked() {
        binding.toolbarContainer.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_eventFragment_to_welcomeFragment)
        }
    }

    private fun navigateToMapFragment() {
        binding.toolbarContainer.imgAdd.setOnClickListener {
            findNavController().navigate(R.id.action_eventFragment_to_mapFragment)
        }
    }

    private fun subscribeToObserver() {
        eventViewModel.getEvents().observe(viewLifecycleOwner) { listEvent ->
            eventAdapter.setData(listEvent)
        }
    }

    private fun setupAdapter() = binding.rvEvent.apply {
        adapter = eventAdapter
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
    }

}