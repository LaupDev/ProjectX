package com.laupdev.projectx.presentation.ui.hotel.details

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.laupdev.projectx.databinding.ViewHotelDetailsGalleryBinding
import com.laupdev.projectx.domain.adapter.PictureAdapter

class HotelDetailsGalleryView(context: Context) : ConstraintLayout(context) {

    constructor(context: Context, adapter: PictureAdapter) : this(context) {
        val binding = ViewHotelDetailsGalleryBinding.inflate(
            LayoutInflater.from(context),
            this,
            true)
        binding.galleryViewPager.adapter = adapter
    }
}