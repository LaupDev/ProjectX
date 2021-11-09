package com.laupdev.projectx.presentation.ui.hotel.details

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.Picture
import com.laupdev.projectx.presentation.ui.hotel.details.adapter.PictureAdapter
import com.laupdev.projectx.domain.repository.IRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class HotelDetailsViewModel(private val repository: IRepository) : ViewModel() {

    var hotelId = 0

    private lateinit var currentHotelPictures: List<Picture>

    private val _currentHotel = MutableLiveData<Hotel>()
    val currentHotel: LiveData<Hotel>
        get() = _currentHotel

    private val _currentHotelContacts = MutableLiveData<ContactInfo>()
    val currentHotelContacts: LiveData<ContactInfo>
        get() = _currentHotelContacts

    suspend fun getHotelsGalleryAndLoadToAdapter(): PictureAdapter {
        currentHotelPictures = repository.getPicturesByHotelId(hotelId)
        return PictureAdapter(currentHotelPictures)
    }

    fun getHotelById() {
        viewModelScope.launch {
            _currentHotel.value = repository.getHotelById(hotelId)
        }
    }

    fun getContactInfoByHotelId() {
        viewModelScope.launch {
            _currentHotelContacts.value = repository.getContactInfoByHotelId(hotelId)
        }
    }

    fun getPage(position: Int, context: Context): View {
        when (position) {
            TabPosition.OVERVIEW.position -> {
                getHotelById()
                return HotelDetailsOverviewView(context, currentHotel)
            }
            TabPosition.GALLERY.position -> {
//                binding.pageView.addView(
//                    HotelDetailsGalleryView(
//                        requireContext(),
//                        viewModel.getHotelsGalleryAndLoadToAdapter()
//                    )
//                )
                return HotelDetailsOverviewView(context, currentHotel)
            }
            TabPosition.CONTACTS.position -> {
                return HotelDetailsOverviewView(context, currentHotel)
            }
            else -> {
                return View(context)
            }
        }
    }

}