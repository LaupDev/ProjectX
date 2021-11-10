package com.laupdev.projectx.presentation.ui.hotel.details

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.Picture
import com.laupdev.projectx.presentation.ui.hotel.details.adapter.PictureAdapter
import com.laupdev.projectx.domain.repository.IRepository
import kotlinx.coroutines.launch

class HotelDetailsViewModel(private val repository: IRepository) : ViewModel() {

    var hotelId = 0

    private lateinit var currentHotelPictures: List<Picture>

    private var pictureAdapter = PictureAdapter(mutableListOf())

    private val currentHotel = MutableLiveData<Hotel>()

    private val currentHotelContacts = MutableLiveData<ContactInfo>()

    fun getPage(position: Int, context: Context): View {
        return when (position) {
            TabPosition.OVERVIEW.position -> {
                loadHotel()
                HotelDetailsOverviewView(context, currentHotel)
            }
            TabPosition.GALLERY.position -> {
                loadHotelPictures()
                HotelDetailsGalleryView(context, pictureAdapter)
            }
            TabPosition.CONTACTS.position -> {
                loadHotelContactInfo()
                HotelDetailsContactsView(context, currentHotelContacts)
            }
            else -> {
                View(context)
            }
        }
    }

    private fun loadHotelPictures() {
        viewModelScope.launch {
            if (!this@HotelDetailsViewModel::currentHotelPictures.isInitialized) {
                currentHotelPictures = repository.getPicturesByHotelId(hotelId)
                pictureAdapter.setData(currentHotelPictures)
            }
        }
    }

    private fun loadHotel() {
        viewModelScope.launch {
            if (currentHotel.value == null) {
                currentHotel.value = repository.getHotelById(hotelId)
            }
        }
    }

    private fun loadHotelContactInfo() {
        viewModelScope.launch {
            if (currentHotelContacts.value == null) {
                currentHotelContacts.value = repository.getContactInfoByHotelId(hotelId)
            }
        }
    }

}