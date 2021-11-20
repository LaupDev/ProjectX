package com.laupdev.projectx.presentation.ui.user

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.laupdev.projectx.R
import com.laupdev.projectx.domain.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class UserViewModel(private val repository: IRepository) : ViewModel() {

    private val authFb = Firebase.auth

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.getUserByEmail(email)
            if (user != null && user.password == password) {
                Timber.d("Local log in")
                authFb.signInWithEmailAndPassword(email, password)
                _isLoggedIn.postValue(true)
            } else {
                Timber.d("FB log in")
                authFb.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _isLoggedIn.postValue(true)
                            handleUserAuthData(email, password)
                        } else {
                            _isLoggedIn.postValue(false)
                        }
                    }
            }
        }
    }

    fun handleUserAuthData(email: String, password: String) {
        viewModelScope.launch {
            repository.handleUserAuthData(email, password)
        }
    }

}