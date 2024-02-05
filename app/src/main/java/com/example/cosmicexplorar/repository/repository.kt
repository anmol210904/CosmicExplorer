package com.example.cosmicexplorar.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.example.cosmicexplorar.apiClasses.apod
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class repository {

    suspend fun getDataFromFirebase(collection: String): ArrayList<apod> {

        var list = arrayListOf<apod>()
        val job = CoroutineScope(Dispatchers.Main).launch {
            val job1 = Firebase.firestore.collection(collection)
                .orderBy("date",Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener {
                    for (i in it.documents) {
                        val temp = i.toObject(apod::class.java)
                        if (temp != null) {
                            list.add(temp)
                        };
                    }
                }
            job1.await()

        }

        job.join()

        return list;
    }



    suspend fun getDataFromFirebase(collection: String , date : String): ArrayList<apod> {
        var list = arrayListOf<apod>()
        val job = CoroutineScope(Dispatchers.Main).launch {
            val job1 = Firebase.firestore.collection(collection)
                .whereEqualTo("date",date)
                .get()
                .addOnSuccessListener {
                    for (i in it.documents) {
                        val temp = i.toObject(apod::class.java)
                        if (temp != null) {
                            list.add(temp)
                        };
                    }
                }
            job1.await()

        }

        job.join()

        return list;
    }

}

