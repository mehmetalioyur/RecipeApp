package com.mehmetalioyur.recipeapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.mehmetalioyur.recipeapp.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                return
            }

            override fun onFinish() {
                val intent = Intent(this@SplashScreenActivity, BaseActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()


    }
}