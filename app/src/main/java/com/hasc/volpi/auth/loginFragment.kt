package com.hasc.volpi.auth

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hasc.volpi.MainActivity
import com.hasc.volpi.commons.utils.Utils.vibration
import com.hasc.volpi.databinding.FragmentLoginBinding


class loginFragment : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var binding: FragmentLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.parseColor("#FDFDFD")


        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding?.tvCreateAccount?.setOnClickListener{
            val intent = Intent(this, registerFragment::class.java )
            startActivity(intent)
        }


        binding?.btnLogin?.setOnClickListener {
            vibration(this)
            val email = binding?.etEmail?.text.toString()
            val password = binding?.etPassword?.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInWithEmailAndPassword(email, password)
            } else {
                Toast.makeText(this, "Por favor, preencha os campos.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signInWithEmailAndPassword(email : String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful) { //    val user = auth.currentUser
                Toast.makeText(baseContext,"Bem vindo a Volpi!", Toast.LENGTH_SHORT).show()
                val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(baseContext,"Conta não cadastrada, crie uma conta!", Toast.LENGTH_SHORT).show()
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}