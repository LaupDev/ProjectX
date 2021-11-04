package com.laupdev.projectx.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laupdev.projectx.domain.repository.IRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class UserViewModel(private val repository: IRepository) : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    fun authenticateUser(login: String, password: String) {
        viewModelScope.launch {
            val user = if (isEmail(login)) {
                repository.getUserByEmail(login)
            } else {
                repository.getUserByUsername(login)
            }
            Timber.e(user.toString())
            _isLoggedIn.value = user != null && user.password == password
        }
    }

    private fun isEmail(userInput: String): Boolean {
        Timber.e(userInput)
        return userInput.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())
    }

}