package com.example.cosmicexplorar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.cosmicexplorar.API.NasaApiInterface
import com.example.cosmicexplorar.API.RetrofitHelper
import com.example.cosmicexplorar.databinding.ActivityEarthBinding
import com.example.cosmicexplorar.repository.EarthRepository
import com.example.cosmicexplorar.viewModel.EarthViewModel
import com.example.cosmicexplorar.viewModel.EarthViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class EARTHActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityEarthBinding.inflate(layoutInflater)
    }



    lateinit var earthViewModel: EarthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initilizing
        val earthservices = RetrofitHelper.getInstance().create(NasaApiInterface :: class.java)
        val repository = EarthRepository(earthservices)
        earthViewModel = ViewModelProvider(this,EarthViewModelFactory(repository)).get(EarthViewModel::class.java)

        binding.dateEditText.listen()



        binding.fetch.setOnClickListener {
            // Get user-entered values using View Binding
            val lat = binding.latitude.text.toString()
            val lon = binding.longitude.text.toString()
            var date = binding.dateEditText.text.toString()

            date = changeDateFormat(date)

            Log.d("TAG", "onCreate: $date")
            earthViewModel.getData(lat,lon,date);
            earthViewModel.earthData.observe(this, Observer {
                Log.d("TAG24", "onCreate: ")
                binding.imageView.load(it.url)
            })
        }

    }





    // date picker
    fun changeDateFormat(inputDate: String): String {
        val parts = inputDate.split("-")

        val day = parts[0]
        val month = parts[1]
        val year = parts[2]

        // Reformat the date
        return "$year-$month-$day"
    }

}