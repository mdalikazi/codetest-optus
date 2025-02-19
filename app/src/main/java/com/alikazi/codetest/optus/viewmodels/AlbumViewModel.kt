package com.alikazi.codetest.optus.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alikazi.codetest.optus.network.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

class AlbumViewModel(private val repository: Repository) : ViewModel() {

    val photos = repository.photosWithUserId

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading get() = _isLoading

    private val _errors = MutableLiveData<Exception>()
    val errors get() = _errors

    fun getAlbumWithUserId(userId: Int) {
        if (userId != -1) {
            viewModelScope.launch {
                try {
                    _isLoading.value = true
                    repository.getAlbumWithUserId(userId)
                } catch (e: Exception) {
                    _errors.value = e
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }

}