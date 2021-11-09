package com.laupdev.projectx.presentation.ui.hotel.details

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.databinding.ViewHotelDetailsOverviewBinding
import com.squareup.picasso.Picasso
import java.io.File

class HotelDetailsOverviewView(context: Context) : ConstraintLayout(context) {

    constructor(context: Context, hotel: Hotel) : this(context) {
        val binding = ViewHotelDetailsOverviewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true)
        Picasso.get().load(File(hotel.imagePath)).into(binding.mainPhotoImage)
        binding.hotelNameText.text = hotel.name
        binding.hotelDescText.text = hotel.description
    }

}