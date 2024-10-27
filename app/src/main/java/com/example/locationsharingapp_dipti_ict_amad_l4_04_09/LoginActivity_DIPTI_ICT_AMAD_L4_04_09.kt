package com.example.locationsharingapp_dipti_ict_amad_l4_04_09

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.example.locationsharingapp_dipti_ict_amad_l4_04_09.databinding.ActivityLoginDiptiIctAmadL40409Binding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class LoginActivity_DIPTI_ICT_AMAD_L4_04_09 : AppCompatActivity() {
    private lateinit var binding: ActivityLoginDiptiIctAmadL40409Binding
    private lateinit var authenticationViewModelDIPTIICTAMADL40409: AuthenticationViewModel_DIPTI_ICT_AMAD_L4_04_09
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginDiptiIctAmadL40409Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        authenticationViewModelDIPTIICTAMADL40409 = ViewModelProvider(this)[AuthenticationViewModel_DIPTI_ICT_AMAD_L4_04_09::class.java]

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Please enter valid password", Toast.LENGTH_SHORT).show()
            } else {
                authenticationViewModelDIPTIICTAMADL40409.login(email, password, {
                    startActivity(Intent(this, MainActivity_DIPTI_ICT_AMAD_L4_04_09::class.java))
                    finish()
                }, {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                })
            }
        }

        binding.registerTxt.setOnClickListener {
            startActivity(Intent(this, RegisterActivity_DIPTI_ICT_AMAD_L4_04_09::class.java))
        }

        binding.forgotpass.setOnClickListener {
            Toast.makeText(this, "Forgot password", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser != null) {
            startActivity(Intent(this@LoginActivity_DIPTI_ICT_AMAD_L4_04_09, MainActivity_DIPTI_ICT_AMAD_L4_04_09::class.java))
            finish()
        }
    }
}