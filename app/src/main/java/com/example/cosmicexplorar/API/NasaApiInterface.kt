package com.example.cosmicexplorar.API

import retrofit2.http.GET
import com.example.cosmicexplorar.apiClasses.earth
import com.example.cosmicexplorar.apiClasses.marsPhotos.marsPhotos
import retrofit2.Response
import retrofit2.http.Query

interface NasaApiInterface {
    // Define default values for parameters
    // query = planetary/earth/assets?lon=100.75&lat=1.5&date=2014-02-01&dim=0.15&api_key=DEMO_KEY
    @GET("planetary/earth/assets")
    suspend fun getEarthData(
        @Query("lon") lon : String,
        @Query("lat") lat : String,
        @Query("date") date : String,
        @Query("dim") dim : String= "0.15",
        @Query("api_key") api : String = "DEMO_KEY"
    ): Response<earth>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMarsPhotosData(
        @Query("earth_date") date : String,
        @Query("api_key") api : String = "DEMO_KEY"
    ) : Response<marsPhotos>
}