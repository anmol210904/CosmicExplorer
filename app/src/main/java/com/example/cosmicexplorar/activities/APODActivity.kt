package com.example.cosmicexplorar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cosmicexplorar.R
import com.example.cosmicexplorar.adapters.apod_adapter
import com.example.cosmicexplorar.apiClasses.apod
import com.example.cosmicexplorar.databinding.ActivityApodactivityBinding

class APODActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityApodactivityBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: apod_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var apodList = arrayListOf<apod>() // ... (initialize your list)
        apodList.add(apod( "2024-01-21",
             "Yes, but can your blizzard do this? In the Upper Peninsula of Michigan's Storm of the Century in 1938, some snow drifts reached the level of utility poles. Nearly a meter of new and unexpected snow fell over two days in a storm that started 86 years ago this week.  As snow fell and gale-force winds piled snow to surreal heights, many roads became not only impassable but unplowable; people became stranded, cars, school buses and a train became mired, and even a dangerous fire raged. Two people were killed and some students were forced to spend several consecutive days at school.  The featured image was taken by a local resident soon after the storm. Although all of this snow eventually melted, repeated snow storms like this help build lasting glaciers in snowy regions of our planet Earth.",
         "https://apod.nasa.gov/apod/image/2401/snowpoles_brinkman_960.jpg",
         "image",
         "v1",
        "The Upper Michigan Blizzard of 1938",
         "https://apod.nasa.gov/apod/image/2401/snowpoles_brinkman_960.jpg"))

        adapter = apod_adapter(this, apodList)

       binding.rcv.layoutManager = LinearLayoutManager(this)
        binding.rcv.adapter = adapter
    }
}