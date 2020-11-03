package com.example.sofie_android_development_test.app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sofie_android_development_test.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        resetActionBar()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        resetActionBar()
    }

    private fun resetActionBar(){
        title = resources.getString(R.string.app_name)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(false)
            it.setDisplayShowHomeEnabled(false)
        }
    }
}
