package com.alikazi.codetest.optus.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alikazi.codetest.optus.network.Repository

class MyViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
            return PhotosViewModel(repository) as T
        }

        throw ClassNotFoundException("Invalid ViewModel in MyViewModelFactory!")
    }
}