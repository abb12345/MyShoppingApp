package com.example.myshoppinapp

 //import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

public class splashActivity : AppCompatActivity() {
    //private val TIME_OUT  : Long = 3000 //Time to launch the another activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({ // TODO: Your application init goes here.
            val mInHome = Intent(this@splashActivity, MainActivity::class.java)
            this@splashActivity.startActivity(mInHome)
            this@splashActivity.finish()
        }, 3000)

        title = "My Shopping App"

    }
}