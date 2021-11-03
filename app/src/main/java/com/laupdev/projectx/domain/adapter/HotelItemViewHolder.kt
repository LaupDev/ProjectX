package com.laupdev.projectx.domain.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.databinding.ItemHotelBinding
import com.squareup.picasso.Picasso
import java.io.File

class HotelItemViewHolder(private val binding: ItemHotelBinding) : RecyclerView.ViewHolder(binding.root) {

    private val imageView: ImageView = binding.hotelPhoto
    private val nameView: TextView = binding.hotelName

    fun bind(hotel: Hotel) {
        Picasso.get().load(File(hotel.imagePath)).fit().into(imageView)
//        imageView.setImageBitmap(BitmapFactory.decodeFile(hotel.imagePath))
        nameView.text = hotel.name
        // TODO: 01.11.2021 Go to Hotel Details page
    }
}