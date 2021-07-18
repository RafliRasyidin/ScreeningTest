package com.rasyidin.screeningtestsuitmedia.ui.event

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rasyidin.screeningtestsuitmedia.R
import com.rasyidin.screeningtestsuitmedia.data.model.Event
import com.rasyidin.screeningtestsuitmedia.databinding.FragmentMapBinding
import com.rasyidin.screeningtestsuitmedia.ui.adapter.EventLocationAdapter
import com.rasyidin.screeningtestsuitmedia.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate),
    OnMapReadyCallback {

    private lateinit var map: GoogleMap

    private val eventViewModel: EventViewModel by viewModels()

    private val eventLocationAdapter: EventLocationAdapter by lazy {
        EventLocationAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()

        val mapFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupAdapter()

        eventLocationAdapter.onItemClickListener = { event ->
            moveCamera(event)
        }

        binding.toolbarContainer.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_mapFragment_to_eventFragment)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        subscribeToObserver()
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarContainer.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = null
        binding.toolbarContainer.tvToolbar.text = getString(R.string.event)
    }

    private fun setupAdapter() = binding.rvEventLocation.apply {
        adapter = eventLocationAdapter
        layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        setHasFixedSize(true)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(this)
    }

    private fun subscribeToObserver() {
        eventViewModel.getEvents().observe(viewLifecycleOwner) { events ->
            eventLocationAdapter.setData(events)

            events.forEach {
                showMarker(it)
            }
        }
    }

    private fun showMarker(event: Event) {
        val latLng = LatLng(event.lat, event.lng)
        map.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(event.name)
                .icon(BitmapDescriptorFactory.defaultMarker())
        )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16F))
    }

    private fun moveCamera(event: Event) {
        val latLng = LatLng(event.lat, event.lng)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16F))
        map.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(event.name)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
        )

    }

}