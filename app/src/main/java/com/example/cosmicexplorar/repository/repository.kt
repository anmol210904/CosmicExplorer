package com.example.cosmicexplorar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cosmicexplorar.apiClasses.apod
import com.example.cosmicexplorar.API.NasaApiInterface
import com.example.cosmicexplorar.API.RetrofitHelper
import com.example.cosmicexplorar.apiClasses.earth
import com.example.cosmicexplorar.apiClasses.marsPhotos.marsPhotos
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class repository() {

    suspend fun getDataFromFirebase(collection: String): ArrayList<apod> {

        var list = arrayListOf<apod>()
        val job = CoroutineScope(Dispatchers.Main).launch {
            val job1 = Firebase.firestore.collection(collection)
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener {
                    for (i in it.documents) {

                        val temp = i.toObject(apod::class.java)
                        if (temp != null) {
                            Log.d("tag", "getDataFromFirebase:${temp.date} ")
                        }
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


    suspend fun getDataFromFirebase(collection: String, date: String): ArrayList<apod> {
        var list = arrayListOf<apod>()
        val job = CoroutineScope(Dispatchers.Main).launch {
            val job1 = Firebase.firestore.collection(collection)
                .whereEqualTo("date", date)
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


class EarthRepository(private val nasaApiInterface: NasaApiInterface){

    private val earthLiveData = MutableLiveData<earth>()
    val earthData : LiveData<earth>
        get() = earthLiveData
    suspend fun getData(lat : String , lon : String , date : String){
        Log.d("TAG23", "onCreate:2 ")
        val result = nasaApiInterface.getEarthData(lon, lat , date);
        Log.d("TAG23", "onCreate:3 ")
        if(result.body() !=  null){
            Log.d("TAG23", "onCreate: ${result.body()!!.url} ")
            earthLiveData.postValue(result.body())
        }
        else{
            Log.d("TAG23", "onCreate:1 ")
        }
    }
}

class MarsPhotosRepository(private val nasaApiInterface: NasaApiInterface){
    private val marsPhotosLiveData = MutableLiveData<marsPhotos>()

    val marsPhotosData : LiveData<marsPhotos>
        get() = marsPhotosLiveData

    suspend fun getData(date : String){
        val result = nasaApiInterface.getMarsPhotosData(date);
        if(result.body() != null){
            marsPhotosLiveData.postValue(result.body())
        }
    }

}


