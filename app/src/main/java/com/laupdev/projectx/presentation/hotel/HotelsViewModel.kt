package com.laupdev.projectx.presentation.hotel

import androidx.lifecycle.*
import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.Picture
import com.laupdev.projectx.domain.adapter.HotelAdapter
import com.laupdev.projectx.domain.adapter.PictureAdapter
import com.laupdev.projectx.domain.repository.IRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class HotelsViewModel(private val repository: IRepository) : ViewModel() {

    private var lastHotelId = 0
    private val size = 10
    var isLoadingAllowed = true
        private set

    val hotelAdapter = HotelAdapter(mutableListOf())
    val pictureAdapter = PictureAdapter(mutableListOf())

    private var currentHotelPictures: List<Picture>? = null

    private val _currentHotel = MutableLiveData<Hotel>()
    val currentHotel: LiveData<Hotel>
        get() = _currentHotel

    private val _currentHotelContacts = MutableLiveData<ContactInfo>()
    val currentHotelContacts: LiveData<ContactInfo>
        get() = _currentHotelContacts

    fun getHotelsPagingAndLoadToAdapter() {
        isLoadingAllowed = false
        viewModelScope.launch {

            with(repository.getHotelsPaging(lastHotelId, size)) {
                Timber.i(size.toString())
                if (isNotEmpty()) {
                    lastHotelId = last().id
                    hotelAdapter.addHotelsToDataset(this)
                    isLoadingAllowed = true
                }
            }

        }
    }

    fun getHotelsGalleryAndLoadToAdapter(hotelId: Int) {
        viewModelScope.launch {
            if (currentHotelPictures == null) {
                currentHotelPictures = repository.getPicturesByHotelId(hotelId)
            }
            pictureAdapter.setData(currentHotelPictures!!)
        }
    }

    fun getHotelById(hotelId: Int) {
        viewModelScope.launch {
            _currentHotel.value = repository.getHotelById(hotelId)
        }
    }

    fun getContactInfoByHotelId(hotelId: Int) {
        viewModelScope.launch {
            _currentHotelContacts.value = repository.getContactInfoByHotelId(hotelId)
        }
    }


}