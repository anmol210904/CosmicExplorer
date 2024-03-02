package com.example.cosmicexplorar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cosmicexplorar.R
import com.example.cosmicexplorar.apiClasses.marsPhotos.Photo


class MarsPhotoAdapter(private val context: Context, private val dataList: List<Photo>) :
    RecyclerView.Adapter<MarsPhotoAdapter.ViewHolder>() {

    // ViewHolder class to hold the views for each item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView1)
    }

    // Inflate the item layout and return a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.marsroverele, parent, false)
        return ViewHolder(itemView)
    }

    // Bind data to the views held by the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.imageView.load(item.img_src)
    }

    // Return the size of your dataset
    override fun getItemCount(): Int {
        return dataList.size
    }
}