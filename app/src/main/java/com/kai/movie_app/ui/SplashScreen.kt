package com.kai.movie_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kai.movie_app.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar!!.hide()

        //delay timer
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            //close splash screen activity
            finish()
        }, 2000)
    }
}