package com.laupdev.projectx.presentation.ui.hotel.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.Picture
import com.laupdev.projectx.domain.adapter.PictureAdapter
import com.laupdev.projectx.domain.repository.IRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class HotelDetailsViewModel(private val repository: IRepository) : ViewModel() {

    var hotelId = 0

    private lateinit var currentHotelPictures: List<Picture>

    private lateinit var currentHotel: Hotel

    private val _currentHotelContacts = MutableLiveData<ContactInfo>()
    val currentHotelContacts: LiveData<ContactInfo>
        get() = _currentHotelContacts

    suspend fun getHotelsGalleryAndLoadToAdapter(): PictureAdapter {
        currentHotelPictures = repository.getPicturesByHotelId(hotelId)
        return PictureAdapter(currentHotelPictures)
    }

    suspend fun getHotelById(): Hotel {
        currentHotel = repository.getHotelById(hotelId)
        return currentHotel
    }

    fun getContactInfoByHotelId() {
        viewModelScope.launch {
            _currentHotelContacts.value = repository.getContactInfoByHotelId(hotelId)
        }
    }

}