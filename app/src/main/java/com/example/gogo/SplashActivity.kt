package com.example.gogo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_DELAY)

        hideActionBar()
    }

    companion object {
        private const val SPLASH_DELAY: Long = 3000
    }
    private fun hideActionBar(){
        supportActionBar?.hide()
    }
}
