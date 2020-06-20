package com.alikazi.codetest.optus.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alikazi.codetest.optus.network.Repository
import com.alikazi.codetest.optus.utils.DLog
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyViewModel(private val repository: Repository) : ViewModel() {

    val users = repository.users
    val photos = repository.photos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading get() = _isLoading

    private val _errors = MutableLiveData<Exception>()
    val errors get() = _errors

    fun getUsers() {
        fetchFromRepository {
            repository.getUsers()
        }
    }

    fun getPhotos() {
        fetchFromRepository {
            repository.getPhotos()
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