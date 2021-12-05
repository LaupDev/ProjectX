package com.laupdev.projectx.presentation.ui.hotels_list

import androidx.lifecycle.*
import com.laupdev.projectx.presentation.ui.hotels_list.adapter.HotelAdapter
import com.laupdev.projectx.domain.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class HotelsListViewModel(private val repository: IRepository) : ViewModel() {

    private var lastHotelId = 0L
    private val size = 10L
    var isLoadingAllowed = true
        private set

    val hotelAdapter = HotelAdapter(mutableListOf())

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

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.signOut()
        }
    }

}