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

        binding.earthEngine.setOnClickListener{
            startActivity(Intent(this,EARTHActivity :: class.java))
        }

        binding.apodButton.setOnClickListener{
            startActivity(Intent(this,APODActivity::class.java))
        }

        binding.marsPhotos.setOnClickListener{
            startActivity(Intent(this, MarsRoverPhotos :: class.java))
        }
    }
}