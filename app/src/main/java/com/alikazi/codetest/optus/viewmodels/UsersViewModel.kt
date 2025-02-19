package com.alikazi.codetest.optus.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alikazi.codetest.optus.network.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(private val repository: Repository) : ViewModel() {

    val users = repository.usersFromDb

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading get() = _isLoading

    private val _errors = MutableLiveData<Exception>()
    val errors get() = _errors

    fun getUsersAndPhotos() {
        fetchFromRepository {
            // Concurrent calls to both APIs
            withContext(viewModelScope.coroutineContext) { repository.getUsersFromNetwork() }
            withContext(viewModelScope.coroutineContext) { repository.getPhotosFromNetwork() }
        }
    }

    private fun fetchFromRepository(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _isLoading.value = true
                block()
            } catch (e: Exception) {
                _errors.value = e
            } finally {
                _isLoading.value = false
            }
        }
    }

}