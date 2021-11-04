package com.laupdev.projectx.presentation.hotel

import androidx.lifecycle.*
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

    private var pictures: List<Picture>? = null

    private val _currentHotel = MutableLiveData<Hotel>()
    val currentHotel: LiveData<Hotel>
        get() = _currentHotel

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
            if (pictures == null) {
                pictures = repository.getPicturesByHotelId(hotelId)
            }
            pictureAdapter.setData(pictures!!)
        }
    }

    fun getHotelById(hotelId: Int) {
        viewModelScope.launch {
            _currentHotel.value = repository.getHotelById(hotelId)
        }
    }


}