package com.laupdev.projectx.domain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.databinding.ItemHotelBinding

class HotelAdapter(private val hotelsList: MutableList<Hotel>) :
    RecyclerView.Adapter<HotelsListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsListItemViewHolder {
        val binding = ItemHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotelsListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelsListItemViewHolder, position: Int) {
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