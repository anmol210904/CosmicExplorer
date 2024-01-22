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

class APODActivityViewModel(application: Application) : AndroidViewModel(application) {
//
    lateinit var list : ArrayList<apod>
    init{
        list = arrayListOf()
        Firebase.firestore.collection("apod").get().addOnSuccessListener {
            if(!it.isEmpty){
                for(i in it.documents){
                    val temp = i.toObject(apod :: class.java)
                    if (temp != null) {
                        list.add(temp)
                    }
                }
            }
        }
    }

    fun getdata(): ArrayList<apod> {
        return list;
    }

}