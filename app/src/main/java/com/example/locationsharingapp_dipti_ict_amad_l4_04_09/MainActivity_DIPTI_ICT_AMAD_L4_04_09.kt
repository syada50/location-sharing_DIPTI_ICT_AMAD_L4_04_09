package com.example.locationsharingapp_dipti_ict_amad_l4_04_09

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.locationsharingapp_dipti_ict_amad_l4_04_09.R.id.friendsFragment12
import com.example.locationsharingapp_dipti_ict_amad_l4_04_09.R.id.profileFragment12

import com.example.locationsharingapp_dipti_ict_amad_l4_04_09.databinding.ActivityMainDiptiIctAmadL40409Binding


import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class MainActivity_DIPTI_ICT_AMAD_L4_04_09 : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainDiptiIctAmadL40409Binding.inflate(layoutInflater)
    }
    private lateinit var actionDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomBar.setupWithNavController(navController)
        binding.drawerNav.setupWithNavController(navController)

        actionDrawerToggle = ActionBarDrawerToggle(
            this, binding.drawerlayout,
            R.string.nav_open,
            R.string.nav_close
        )
        actionDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.drawerNav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logout -> {
                    Firebase.auth.signOut()
                    startActivity(
                        Intent(this, LoginActivity_DIPTI_ICT_AMAD_L4_04_09::class.java)
                    )
                    finish()
                }
                R.id.profileFragment12 -> {
                    navController.navigate(profileFragment12)

                }
                friendsFragment12 -> {
                    navController.navigate(friendsFragment12)
                }

            }
            true
        }
        binding.bottomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.logout -> {
                    Firebase.auth.signOut()
                    startActivity(
                        Intent(this, LoginActivity_DIPTI_ICT_AMAD_L4_04_09::class.java)
                    )
                    finish()
                }
                friendsFragment12 -> {
                    navController.navigate(friendsFragment12)
                }
                profileFragment12 -> {
                    navController.navigate(R.id.profileFragment12)
                }

            }
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}