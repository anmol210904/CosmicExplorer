package com.example.cosmicexplorar.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmicexplorar.adapters.apod_adapter
import com.example.cosmicexplorar.apiClasses.apod
import com.example.cosmicexplorar.repository.repository
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlin.math.log

class APODActivityViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var list1 : ArrayList<apod>
    init {
        list1 = arrayListOf();
    }
    suspend fun getData(date : String ): ArrayList<apod> {
        val job = CoroutineScope(Dispatchers.IO).async {
            var list : ArrayList<apod> = arrayListOf();
            if(date == ""){
                list.addAll(repository().getDataFromFirebase("apod"))


            }
            else{
                list.addAll(repository().getDataFromFirebase("apod",date))
            }
            list
        }
        Log.d("final tag", "getData: ${job.await().size}")
        return job.await();
    }

}