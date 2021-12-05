package com.laupdev.projectx.presentation.ui.hotel

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.Picture
import com.laupdev.projectx.presentation.ui.hotel.gallery.adapter.PictureAdapter
import com.laupdev.projectx.domain.repository.IRepository
import com.laupdev.projectx.presentation.ui.hotel.contacts.HotelDetailsContactsView
import com.laupdev.projectx.presentation.ui.hotel.gallery.HotelDetailsGalleryView
import com.laupdev.projectx.presentation.ui.hotel.overview.HotelDetailsOverviewView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelDetailsViewModel(private val repository: IRepository) : ViewModel() {

    var hotelId = 0L

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

    private fun loadHotel() {
        viewModelScope.launch(Dispatchers.IO) {
            if (currentHotel.value == null) {
                currentHotel.postValue(repository.getHotelById(hotelId))
            }
        }
    }

    private fun loadHotelContactInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            if (currentHotelContacts.value == null) {
                currentHotelContacts.postValue(repository.getContactInfoByHotelId(hotelId))
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

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.signOut()
        }
    }

}