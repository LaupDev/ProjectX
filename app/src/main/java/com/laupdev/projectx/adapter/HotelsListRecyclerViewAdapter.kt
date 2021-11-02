package com.laupdev.projectx.adapter

import android.net.Uri.parse
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.R
import com.laupdev.projectx.database.Hotel

class HotelsListRecyclerViewAdapter(private val hotelsList: List<Hotel>) :
    RecyclerView.Adapter<HotelsListRecyclerViewAdapter.HotelItemViewHolder>() {

    class HotelItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.hotel_photo)
        val nameView: TextView = view.findViewById(R.id.hotel_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel, parent, false)
        return HotelItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelItemViewHolder, position: Int) {
        holder.imageView.setImageURI(parse(hotelsList[position].imagePath))
        holder.nameView.text = hotelsList[position].name
        holder.view.setOnClickListener {
            // TODO: 01.11.2021 Go to Hotel Details page
        }
    }

    override fun getItemCount(): Int {
        return hotelsList.size
    }
}