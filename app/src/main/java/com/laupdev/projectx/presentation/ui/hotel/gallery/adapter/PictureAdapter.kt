package com.laupdev.projectx.presentation.ui.hotel.gallery.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.data.database.Picture
import com.laupdev.projectx.databinding.ItemPictureBinding

class PictureAdapter(private var pictures: List<Picture>) : RecyclerView.Adapter<PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = ItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(pictures[position])
    }

    override fun getItemCount(): Int {
        return pictures.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newPictures: List<Picture>) {
        pictures = newPictures
        notifyDataSetChanged()
    }
}