package com.example.cosmicexplorar.interfaces

import retrofit2.Call
import retrofit2.http.GET
import com.example.cosmicexplorar.apiClasses.earth
import retrofit2.http.Query

interface earth_interface {
    // Define default values for parameters
    // query = assets?lon=100.75&lat=1.5&date=2014-02-01&dim=0.15&api_key=DEMO_KEY
    @GET("assets")
    fun getData(
        @Query("lon") lon : String,
        @Query("lat") lat : String,
        @Query("date") date : String,
        @Query("dim") dim : String= "0.15",
        @Query("api_key") api : String = "DEMO_KEY"
    ): Call<earth>
}