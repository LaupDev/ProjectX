package com.laupdev.projectx.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.R
import com.laupdev.projectx.database.Hotel
import com.laupdev.projectx.databinding.ItemHotelBinding

class HotelItemViewHolder(private val binding: ItemHotelBinding) : RecyclerView.ViewHolder(binding.root) {

    private val imageView: ImageView = binding.hotelPhoto
    private val nameView: TextView = binding.hotelName

    fun bind(hotel: Hotel) {
        imageView.setImageURI(Uri.parse(hotel.imagePath))
        nameView.text = hotel.name
        // TODO: 01.11.2021 Go to Hotel Details page
    }
}