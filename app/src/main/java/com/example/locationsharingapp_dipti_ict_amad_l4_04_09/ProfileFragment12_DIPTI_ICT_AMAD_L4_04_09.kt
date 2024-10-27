package com.example.locationsharingapp_dipti_ict_amad_l4_04_09

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.locationsharingapp_dipti_ict_amad_l4_04_09.databinding.FragmentProfileDiptiIctAmadL40409Binding

import com.google.firebase.auth.FirebaseAuth



class ProfileFragment12_DIPTI_ICT_AMAD_L4_04_09 : Fragment() {

    private lateinit var binding: FragmentProfileDiptiIctAmadL40409Binding
    private lateinit var authViewModel: AuthenticationViewModel_DIPTI_ICT_AMAD_L4_04_09
    private lateinit var firestoreViewModelDIPTIICTAMADL40409: FirestoreViewModel_DIPTI_ICT_AMAD_L4_04_09
    private lateinit var locationViewModelDIPTIICTAMADL40409: LocationViewModel_DIPTI_ICT_AMAD_L4_04_09
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileDiptiIctAmadL40409Binding.inflate(inflater, container, false)
        authViewModel = ViewModelProvider(this).get(AuthenticationViewModel_DIPTI_ICT_AMAD_L4_04_09::class.java)
        firestoreViewModelDIPTIICTAMADL40409 = ViewModelProvider(this).get(FirestoreViewModel_DIPTI_ICT_AMAD_L4_04_09::class.java)
        locationViewModelDIPTIICTAMADL40409 = ViewModelProvider(this).get(LocationViewModel_DIPTI_ICT_AMAD_L4_04_09::class.java)

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(requireContext(), LoginActivity_DIPTI_ICT_AMAD_L4_04_09::class.java))
        }
        binding.homeBtn.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity_DIPTI_ICT_AMAD_L4_04_09::class.java))
        }
        loadUserInfo()

        binding.updateBtn.setOnClickListener {
            val newName = binding.nameEt.text.toString()
            val newLocation = binding.locationEt.text.toString()

            updateProfile(newName, newLocation)
        }

        return binding.root
    }

    private fun updateProfile(newName: String, newLocation: String) {
        val currentUser = authViewModel.getCurrentUser()
        if (currentUser != null) {
            val userId = currentUser.uid
            firestoreViewModelDIPTIICTAMADL40409.updateUser(requireContext(),userId, newName, newLocation)
            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), MainActivity_DIPTI_ICT_AMAD_L4_04_09::class.java))
        } else {
            // Handle the case where the current user is null
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadUserInfo() {
        val currentUser = authViewModel.getCurrentUser()
        if (currentUser != null) {
            binding.emailEt.setText(currentUser.email)

            firestoreViewModelDIPTIICTAMADL40409.getUser(requireContext(),currentUser.uid) {
                if (it != null) {
                    binding.nameEt.setText(it.displayName)

                    firestoreViewModelDIPTIICTAMADL40409.getUserLocation(requireContext(),currentUser.uid) {
                        if (it.isNotEmpty()) {
                            binding.locationEt.setText(it)
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()

                }
            }
        } else {
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()

        }
    }


}