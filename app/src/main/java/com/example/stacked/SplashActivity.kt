package com.example.stacked

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    // sets the layout to the splash screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // delays the screen for 2 seconds then goes to MainActivity to open main screen
        Handler(Looper.getMainLooper()).postDelayed({
            // starts main activity
            startActivity(Intent(this, MainActivity::class.java))
            // closes splash activity
            finish()
        }, 2000) // 2 second delay
    }
}
