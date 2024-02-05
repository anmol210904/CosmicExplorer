package com.example.cosmicexplorar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cosmicexplorar.R
import com.example.cosmicexplorar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.setOnClickListener{
            startActivity(Intent(this,EARTHActivity :: class.java))
        }

        binding.textView2.setOnClickListener{
            startActivity(Intent(this,APODActivity::class.java))
        }
    }
}