package com.laupdev.projectx.presentation.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laupdev.projectx.domain.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartViewModel(private val repository: IRepository) : ViewModel() {

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn

    init {
        isUserLoggedIn()
    }

    private fun isUserLoggedIn() {
        viewModelScope.launch(Dispatchers.IO) {
            _isUserLoggedIn.postValue(repository.getLoggedInUser() != null)
        }
    }

}