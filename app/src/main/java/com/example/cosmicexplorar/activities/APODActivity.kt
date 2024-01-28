package com.example.cosmicexplorar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmicexplorar.R
import com.example.cosmicexplorar.adapters.apod_adapter
import com.example.cosmicexplorar.apiClasses.apod
import com.example.cosmicexplorar.databinding.ActivityApodactivityBinding
import com.example.cosmicexplorar.viewModel.APODActivityViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class APODActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityApodactivityBinding.inflate(layoutInflater)
    }
    lateinit var list1 : ArrayList<apod>
    lateinit var apodList: ArrayList<apod>
    lateinit var date : String
    private lateinit var adapter: apod_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        date = "";
        apodList = arrayListOf<apod>()
        loadintoadapter(date);
        loadingbar("start")

        binding.seachbar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                    val input = newText.toString()
                    searchviewload(input)
                return false
            }

        })

        binding.floatingActionButton.setOnClickListener{
            datepicker()
        }

    }


    fun loadintoadapter(date : String) {
        apodList.clear()
        list1 = arrayListOf()
        if(date != ""){
            Firebase.firestore.collection("apod")

                .orderBy("date", Query.Direction.DESCENDING)
                .whereEqualTo("date",date)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val temp = document.toObject(apod::class.java)
                        if (temp != null) {
                            apodList.add(temp)
                            list1.add(temp)
                        }
                    }
                    loadingbar("end")
                    // Initialize or update the adapter here
                    adapter = apod_adapter(this, list1)
                    binding.rcv.layoutManager = LinearLayoutManager(this)
                    binding.rcv.adapter = adapter
                }.addOnFailureListener { exception ->
                    // Handle failure if needed
                    Log.e("tag", "Error fetching data: $exception")
                }
        }
        if(date == ""){
            Firebase.firestore.collection("apod")

                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val temp = document.toObject(apod::class.java)
                        if (temp != null) {
                            apodList.add(temp)
                            list1.add(temp)
                        }
                    }
                    loadingbar("end")
                    // Initialize or update the adapter here
                    adapter = apod_adapter(this, list1)
                    binding.rcv.layoutManager = LinearLayoutManager(this)
                    binding.rcv.adapter = adapter
                }.addOnFailureListener { exception ->
                    // Handle failure if needed
                    Log.e("tag", "Error fetching data: $exception")
                }
        }
    }

    fun searchviewload(input: String) {
        list1.clear()
        val input1 = input.lowercase()
        for(i in apodList){
            val title = i.title.lowercase()
            if(title.contains(input1)){
                list1.add(i);
            }
        }

        adapter.notifyDataSetChanged()

    }

    fun datepicker(){
        loadingbar("start")
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        datePicker.show(supportFragmentManager, "DatePicker")

        // Setting up the event for when ok is clicked
        datePicker.addOnPositiveButtonClickListener {
            // formatting date in dd-mm-yyyy format.
            val dateFormatter = SimpleDateFormat("YYYY-MM-DD")

            date = dateFormatter.format(Date(it))
            loadintoadapter(date);
            loadingbar("end")

        }

        // Setting up the event for when cancelled is clicked
        datePicker.addOnNegativeButtonClickListener {
            Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
            loadintoadapter("")
//            loadingbar("end")
        }

        // Setting up the event for when back button is pressed
        datePicker.addOnCancelListener {
//            Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            loadintoadapter("")
            loadingbar("end")
        }
    }

    fun loadingbar(input : String){
        if(input == "start"){
            binding.rcv.visibility = View.GONE
            binding.floatingActionButton.visibility = View.GONE
//            binding.loading.visibility = View.VISIBLE
        }
        else if(input == "end"){
            binding.rcv.visibility = View.VISIBLE
            binding.floatingActionButton.visibility = View.VISIBLE
//            binding.loading.visibility = View.GONE
        }
    }


}

