package com.example.cosmicexplorar.adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cosmicexplorar.R
import com.example.cosmicexplorar.apiClasses.apod

class apod_adapter(private val context: Context, private val itemList: List<apod>) :
    RecyclerView.Adapter<apod_adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.apod_rcv_ele, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.text1.text = item.title
        holder.text2.text = "Date : " + item.date
        holder.text3.text = item.explanation
        holder.text4.text = "Read More"
        holder.imageView.load(item.url)
        holder.text4.setOnClickListener {
            // Set the layout params of text3 to WRAP_CONTENT
        if (holder.text4.text == "Read More"){
            val layoutParams = holder.text3.layoutParams
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            holder.text3.layoutParams = layoutParams
            holder.text4.text = "Read Less"
        }

            else{
            val layoutParams = holder.text3.layoutParams
            layoutParams.apply {
                height = 100
            }
            holder.text3.layoutParams = layoutParams
            holder.text4.text = "Read More"
            }
        }
    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1: TextView = itemView.findViewById(R.id.title)
        val text2: TextView = itemView.findViewById(R.id.date)
        val text3: TextView = itemView.findViewById(R.id.desp)
        val text4: TextView = itemView.findViewById(R.id.readMore)
        val imageView: ImageView = itemView.findViewById(R.id.image)
    }
}
