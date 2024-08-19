package com.hasc.volpi

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.hasc.volpi.commons.utils.Utils.vibration
import com.hasc.volpi.auth.loginFragment
import com.hasc.volpi.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private var binding : ActivityWelcomeBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding?.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.parseColor("#FFFFFF")


        binding?.buttonMainStart?.setOnClickListener {
            vibration(this)
            val intent = Intent(this, loginFragment::class.java)
            startActivity(intent)
        }

    }

}

