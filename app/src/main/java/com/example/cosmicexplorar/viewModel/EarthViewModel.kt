package com.example.cosmicexplorar.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cosmicexplorar.apiClasses.earth
import com.example.cosmicexplorar.repository.EarthRepository
import com.example.cosmicexplorar.repository.repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class EarthViewModel(val earthRepository: EarthRepository) : ViewModel(){
    init {

    }

    val earthData : LiveData<earth>
        get() = earthRepository.earthData

    fun getData(lat : String, lon : String, date : String){

        CoroutineScope(Dispatchers.Main).launch {
            earthRepository.getData(lat, lon, date)

        }
    }

}
