package com.example.cosmicexplorar.activities

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmicexplorar.API.NasaApiInterface
import com.example.cosmicexplorar.API.RetrofitHelper
import com.example.cosmicexplorar.R
import com.example.cosmicexplorar.adapters.MarsPhotoAdapter
import com.example.cosmicexplorar.apiClasses.marsPhotos.Photo
import com.example.cosmicexplorar.databinding.ActivityMarsRoverPhotosBinding
import com.example.cosmicexplorar.repository.EarthRepository
import com.example.cosmicexplorar.repository.MarsPhotosRepository
import com.example.cosmicexplorar.viewModel.EarthViewModel
import com.example.cosmicexplorar.viewModel.EarthViewModelFactory
import com.example.cosmicexplorar.viewModel.MarsPhotosViewModel
import com.example.cosmicexplorar.viewModel.MarsViewModelFactory

class MarsRoverPhotos : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MarsPhotoAdapter
    private lateinit var dataList: ArrayList<Photo>

    lateinit var marsPhotosViewModel: MarsPhotosViewModel
    val binding by lazy {
        ActivityMarsRoverPhotosBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val marsPhotosServicies = RetrofitHelper.getInstance().create(NasaApiInterface :: class.java)
        val repository = MarsPhotosRepository(marsPhotosServicies)
        marsPhotosViewModel = ViewModelProvider(this, MarsViewModelFactory(repository)).get(
            MarsPhotosViewModel::class.java)

        dataList = arrayListOf<Photo>()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = MarsPhotoAdapter(this, dataList)
        binding.recyclerView.adapter = adapter

        binding.dateEditText.listen()

        marsPhotosViewModel.getData("2024-01-20")

        binding.fetch.setOnClickListener{
            var date = binding.dateEditText.text.toString()
            date = changeDateFormat(date)
            marsPhotosViewModel.getData(date)
            binding.recyclerView.visibility = View.GONE

        }

        marsPhotosViewModel.marsData.observe(this){
            Log.d("TAG","${it.photos.size}")
            dataList.clear()
            dataList.addAll(it.photos)
            if(dataList.size > 0){
                binding.empty.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
            else{
                binding.empty.visibility = View.VISIBLE
            }
            adapter.notifyDataSetChanged()
        }

    }



    fun changeDateFormat(inputDate: String): String {
        val parts = inputDate.split("-")

        val day = parts[0]
        val month = parts[1]
        val year = parts[2]

        // Reformat the date
        return "$year-$month-$day"
    }




}