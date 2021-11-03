package com.laupdev.projectx.domain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.databinding.ItemHotelBinding

class HotelAdapter(private val hotelsList: MutableList<Hotel>) :
    RecyclerView.Adapter<HotelItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelItemViewHolder {
        val binding = ItemHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotelItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelItemViewHolder, position: Int) {
        holder.bind(hotelsList[position])
    }

    override fun getItemCount(): Int {
        return hotelsList.size
    }

    fun addHotelsToDataset(newHotels: List<Hotel>) {
        hotelsList.addAll(newHotels)
        notifyItemRangeInserted(hotelsList.size, newHotels.size)
    }
}