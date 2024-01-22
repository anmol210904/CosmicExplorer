package com.example.cosmicexplorar.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.example.cosmicexplorar.apiClasses.apod
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class repository {

//    fun getFirebaseDocument(collection: String): ArrayList<apod> {
//        var list: ArrayList<apod> = arrayListOf();
//        Firebase.firestore.collection(collection).get().addOnSuccessListener {
//
//            for (i in it.documents) {
//                val data: apod? = i.toObject(apod::class.java)
//                if (data != null) {
//                    list.add(data)
//                };
//            }
//
//        }
//        Log.e("anmol", list.isEmpty().toString(), )
//        return list
//    }

    fun getFirebaseDocument(collection: String, callback: (ArrayList<apod>) -> Unit) {
        var list: ArrayList<apod> = arrayListOf()

        Firebase.firestore.collection(collection).get().addOnSuccessListener { querySnapshot ->
            for (i in querySnapshot.documents) {
                val data: apod? = i.toObject(apod::class.java)
                if (data != null) {
                    list.add(data)
                }
            }

            Log.e("anmol", list.isEmpty().toString())
            callback(list)
        }.addOnFailureListener {
            // Handle failure if needed
            Log.e("anmol", "Error fetching data: $it")
            callback(ArrayList()) // or callback(null) depending on your needs
        }
    }

}