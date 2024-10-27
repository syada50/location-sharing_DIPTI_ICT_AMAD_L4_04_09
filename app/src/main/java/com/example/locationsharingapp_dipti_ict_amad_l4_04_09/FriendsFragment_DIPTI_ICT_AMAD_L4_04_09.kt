package com.example.locationsharingapp_dipti_ict_amad_l4_04_09

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locationsharingapp_dipti_ict_amad_l4_04_09.databinding.FragmentFriendsDiptiIctAmadL40409Binding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class FriendsFragment_DIPTI_ICT_AMAD_L4_04_09 : Fragment() {
    private lateinit var binding: FragmentFriendsDiptiIctAmadL40409Binding
    private lateinit var firestoreViewModelDIPTIICTAMADL40409: FirestoreViewModel_DIPTI_ICT_AMAD_L4_04_09
    private lateinit var authenticationViewModelDIPTIICTAMADL40409: AuthenticationViewModel_DIPTI_ICT_AMAD_L4_04_09
    private lateinit var userAdapterDIPTIICTAMADL40409: UserAdapter_DIPTI_ICT_AMAD_L4_04_09
    private lateinit var locationViewModelDIPTIICTAMADL40409: LocationViewModel_DIPTI_ICT_AMAD_L4_04_09
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getLocation()
            } else {
                Toast.makeText(requireContext(), "Location Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFriendsDiptiIctAmadL40409Binding.inflate(inflater,container, false)

        firestoreViewModelDIPTIICTAMADL40409 = ViewModelProvider(this).get(FirestoreViewModel_DIPTI_ICT_AMAD_L4_04_09::class.java)
        locationViewModelDIPTIICTAMADL40409 = ViewModelProvider(this).get(LocationViewModel_DIPTI_ICT_AMAD_L4_04_09::class.java)
        authenticationViewModelDIPTIICTAMADL40409 = ViewModelProvider(this).get(AuthenticationViewModel_DIPTI_ICT_AMAD_L4_04_09::class.java)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        locationViewModelDIPTIICTAMADL40409.initializeFusedLocationClient(fusedLocationClient)

        // Check if location permission is granted
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request the permission
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            // Permission is already granted
            getLocation()
        }
        userAdapterDIPTIICTAMADL40409 = UserAdapter_DIPTI_ICT_AMAD_L4_04_09(emptyList())
        binding.userRV.apply {
            adapter = userAdapterDIPTIICTAMADL40409
            layoutManager = LinearLayoutManager(requireContext())
        }

        fetchUsers()

        binding.locationBtn.setOnClickListener {
            startActivity(Intent(requireContext(),MapsActivity_DIPTI_ICT_AMAD_L4_04_09::class.java))
        }


        return binding.root
    }

    private fun fetchUsers() {
        firestoreViewModelDIPTIICTAMADL40409.getAllUsers(requireContext()){
            userAdapterDIPTIICTAMADL40409.updateData(it)
        }
    }

    private fun getLocation() {
        locationViewModelDIPTIICTAMADL40409.getLastLocation {
            // Save location to Firestore for the current user
            authenticationViewModelDIPTIICTAMADL40409.getCurrentUserId().let { userId ->
                firestoreViewModelDIPTIICTAMADL40409.updateUserLocation(requireContext(),userId, it)
            }
        }
    }

}