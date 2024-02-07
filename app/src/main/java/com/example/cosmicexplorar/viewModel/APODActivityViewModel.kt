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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

class APODActivityViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var list : ArrayList<apod>
    init {
        list = arrayListOf()
    }
    suspend fun getData(date : String ): ArrayList<apod> {
        if(list.size != 0){

            return list;

        }
        else{

            val job = CoroutineScope(Dispatchers.IO).async {

                if(date == ""){
                    list.addAll(repository().getDataFromFirebase("apod"))


                }
                else{
                    list.addAll(repository().getDataFromFirebase("apod",date))
                }
                list
            }

            return job.await();
        }
    }

}