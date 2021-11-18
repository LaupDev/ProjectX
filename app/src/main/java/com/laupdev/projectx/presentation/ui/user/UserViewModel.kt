package com.laupdev.projectx.presentation.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laupdev.projectx.domain.repository.IRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: IRepository) : ViewModel() {

    fun handleUserAuthData(email: String, password: String) {
        viewModelScope.launch {
            repository.handleUserAuthData(email, password)
        }
    }

}