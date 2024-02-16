package com.prueba.pruebaapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prueba.pruebaapp.R
import com.prueba.pruebaapp.databinding.ActivityLoginBinding
import com.prueba.pruebaapp.ui.home.MainActivity
import com.prueba.pruebaapp.util.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (binding.inputUsername.text.toString() ==("Admin") && binding.inputPassword.text.toString() ==("Password*123")){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else toast(getString(R.string.login_error))
        }
    }
}