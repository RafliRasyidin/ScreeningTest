package com.rasyidin.screeningtestsuitmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rasyidin.screeningtestsuitmedia.databinding.ActivityMainBinding
import com.rasyidin.screeningtestsuitmedia.di.AppPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navAppController: NavController

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var pref: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navAppController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
    }

    override fun onStop() {
        super.onStop()
        pref.removeEventPref()
    }
}