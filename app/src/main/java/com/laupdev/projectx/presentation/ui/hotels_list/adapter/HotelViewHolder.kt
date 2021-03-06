package com.laupdev.projectx.presentation.ui.hotels_list.adapter

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.databinding.ItemHotelBinding
import com.laupdev.projectx.presentation.ui.hotels_list.HotelsListFragmentDirections
import com.squareup.picasso.Picasso
import java.io.File

class HotelViewHolder(private val binding: ItemHotelBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hotel: Hotel) {
        Picasso.get().load(File(hotel.imagePath)).fit().into(binding.hotelPhoto)
        binding.hotelName.text = hotel.name
        binding.root.setOnClickListener {
            val action = HotelsListFragmentDirections.goToHotelDetailsFragment(hotel.id)
            binding.root.findNavController().navigate(action)
        }
    }
}