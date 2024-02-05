package com.example.cosmicexplorar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.example.cosmicexplorar.R
import com.example.cosmicexplorar.apiClasses.earth
import com.example.cosmicexplorar.databinding.ActivityEarthBinding
import com.example.cosmicexplorar.interfaces.earth_interface
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date

class EARTHActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityEarthBinding.inflate(layoutInflater)
    }
    private var date : String?  = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.date.setOnClickListener{
            datepicker()
        }


        binding.fetch.setOnClickListener {
            // Get user-entered values using View Binding
            val lat = binding.latitude.text.toString()
            val lon = binding.longitude.text.toString()


            val retrofitBuilder = Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/planetary/earth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(earth_interface :: class.java)

            if(lon != ""&& lat != "" && date != null) {
                val retrofitData = retrofitBuilder.getData(lon = lon, lat = lat, date = date!!)

                retrofitData.enqueue(object : Callback<earth?> {
                    override fun onResponse(call: Call<earth?>, response: Response<earth?>) {
                        val reponsebody = response.body()
                        if (reponsebody != null) {
                            Toast.makeText(this@EARTHActivity, reponsebody.url, Toast.LENGTH_SHORT)
                                .show()
                            binding.imageView.load(reponsebody.url)
                        }
                        else{
                            Toast.makeText(this@EARTHActivity, "No data Available", Toast.LENGTH_SHORT).show()
                        }


                    }

                    override fun onFailure(call: Call<earth?>, t: Throwable) {
                        Toast.makeText(this@EARTHActivity, "error", Toast.LENGTH_SHORT).show()
                    }
                })

            }

            else{
                Toast.makeText(this, "Fill all the feilds", Toast.LENGTH_SHORT).show()
            }
        }

    }





    // date picker
    fun datepicker(){

        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        datePicker.show(supportFragmentManager, "DatePicker")

        // Setting up the event for when ok is clicked
        datePicker.addOnPositiveButtonClickListener {
            // formatting date in dd-mm-yyyy format.
            val dateFormatter = SimpleDateFormat("YYYY-MM-DD")

            date = dateFormatter.format(Date(it))

            val temp = ("Date : "+date)
            binding.date.text = temp


        }

        // Setting up the event for when cancelled is clicked
        datePicker.addOnNegativeButtonClickListener {
            Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()

        }

        // Setting up the event for when back button is pressed
        datePicker.addOnCancelListener {
//            Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_LONG).show()

        }
    }
}