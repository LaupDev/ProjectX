package com.laupdev.projectx.domain.viewmodel

import androidx.lifecycle.*
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.domain.adapter.HotelAdapter
import com.laupdev.projectx.domain.repository.IRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class HotelsViewModel(private val repository: IRepository) : ViewModel() {

    private var lastHotelId = 0
    private val size = 10
    var isLoadingAllowed = true
        private set

    val hotelAdapter = HotelAdapter(mutableListOf())

    private val _hotelWithAllInfo = MutableLiveData<Hotel>()
    val hotelWithAllInfo: LiveData<Hotel>
        get() = _hotelWithAllInfo

    init {
        Timber.i("init: getHotelsPaging()")
        getHotelsPaging()
    }

    fun getHotelsPaging() {
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

    fun getHotelWithAllInfo(hotelId: Int) {
        viewModelScope.launch {
            _hotelWithAllInfo.value = repository.getHotelWithAllInfo(hotelId)
        }
    }


}