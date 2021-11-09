package com.laupdev.projectx.presentation.ui.hotel.details.adapter

import androidx.recyclerview.widget.RecyclerView
import com.laupdev.projectx.data.database.Picture
import com.laupdev.projectx.databinding.ItemPictureBinding
import com.squareup.picasso.Picasso
import java.io.File

class PictureViewHolder(private val binding: ItemPictureBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(picture: Picture) {
        Picasso.get().load(File(picture.imagePath)).fit().into(binding.root)
    }
}