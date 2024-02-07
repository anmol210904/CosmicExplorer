package com.example.cosmicexplorar.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.cosmicexplorar.adapters.apod_adapter
import com.example.cosmicexplorar.apiClasses.apod
import com.example.cosmicexplorar.databinding.ActivityApodactivityBinding
import com.example.cosmicexplorar.viewModel.APODActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.log

class APODActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityApodactivityBinding.inflate(layoutInflater)
    }
    lateinit var adapter: apod_adapter
    var itemList : ArrayList<apod> = arrayListOf()
    var tempList : ArrayList<apod> = arrayListOf()
    var cal = Calendar.getInstance()

    private lateinit var apodActivityViewModel: APODActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // basic variable initilisation
        apodActivityViewModel = ViewModelProvider(this).get(APODActivityViewModel :: class.java)
         adapter = apod_adapter(this ,tempList)
        binding.rcv.adapter = adapter
        binding.rcv.layoutManager = LinearLayoutManager(this)



        //to get data
        CoroutineScope(Dispatchers.Main).launch {
            getDataForRcv("")
        }


        //searchbar action

        binding.seachbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                    searchBarAction(newText.toString())

                return true
            }

        })



    }


    suspend fun getDataForRcv(date : String ){
        itemList.clear()
        tempList.clear()
        val job  = CoroutineScope(Dispatchers.Main).launch {
            itemList.addAll(apodActivityViewModel.getData(date))
            tempList.addAll(itemList);
        }
        job.join()

        adapter.notifyDataSetChanged()
    }


    //searchbar
    private fun searchBarAction(input: String) {
        tempList.clear();
        for(i in itemList){
            if(input in i.title.lowercase()){
                tempList.add(i);
            }
        }
        adapter.notifyDataSetChanged()
    }






}

