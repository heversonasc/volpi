package com.hasc.volpi.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hasc.volpi.commons.utils.Utils.vibration
import com.hasc.volpi.databinding.FragmentRegisterBinding


class registerFragment : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var binding : FragmentRegisterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.parseColor("#FDFDFD")

        binding?.btnRegister?.setOnClickListener {
            vibration(this)
            val email: String = binding?.etEmail?.text.toString()
            val password: String = binding?.etPassword?.text.toString()
            val confirmPassword: String = binding?.etConfirmPassword?.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword) {
                    createUserWithEmailAndPassword(email, password)
                } else {
                    Toast.makeText(this, "Senhas incompativeis", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createUserWithEmailAndPassword(email : String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Conta criada com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, loginFragment::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(baseContext, "Authentication Failure, tente novamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}