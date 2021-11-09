package com.laupdev.projectx.presentation.ui.hotel.details

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.databinding.ViewHotelDetailsOverviewBinding
import com.squareup.picasso.Picasso
import java.io.File

class HotelDetailsOverviewView(context: Context) : ConstraintLayout(context) {

    private lateinit var hotel: LiveData<Hotel>

    private var binding: ViewHotelDetailsOverviewBinding = ViewHotelDetailsOverviewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    private val observer = Observer<Hotel>{
        it?.let {
            Picasso.get().load(File(it.imagePath)).into(binding.mainPhotoImage)
            binding.hotelNameText.text = it.name
            binding.hotelDescText.text = it.description
        }
    }

    constructor(context: Context, hotel: LiveData<Hotel>) : this(context) {
        this.hotel = hotel
        this.hotel.observeForever(observer)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        hotel.removeObserver(observer)
    }

}