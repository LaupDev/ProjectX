package com.laupdev.projectx.model

import androidx.lifecycle.*
import com.laupdev.projectx.database.Hotel
import kotlinx.coroutines.launch
import timber.log.Timber

class HotelsViewModel(private val hotelsRepository: HotelsRepository) : ViewModel() {

    private var lastHotelId = 0
    private val size = 10
    var isLoadingAllowed = true

    private val _hotelsList = MutableLiveData<List<Hotel>>()
    val hotelsList: LiveData<List<Hotel>>
        get() = _hotelsList

    init {
        Timber.i("init: getHotelsPaging()")
        getHotelsPaging()
    }

    fun getHotelsPaging() {
        isLoadingAllowed = false
        viewModelScope.launch {

            with(hotelsRepository.getHotelsPaging(lastHotelId, size)) {
                Timber.i(size.toString())
                if (isNotEmpty()) {
                    lastHotelId = last().id
                    _hotelsList.value = this
                    isLoadingAllowed = true
                }
            }

        }
    }


}