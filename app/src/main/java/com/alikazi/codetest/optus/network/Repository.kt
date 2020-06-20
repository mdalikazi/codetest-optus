package com.alikazi.codetest.optus.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.alikazi.codetest.optus.database.AppDatabase
import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.models.User
import com.alikazi.codetest.optus.utils.DLog

class Repository(private val database: AppDatabase) {

    companion object {
        private lateinit var repository: Repository
        fun getInstance(database: AppDatabase): Repository {
            if (!::repository.isInitialized) {
                repository = Repository(database)
            }
            return repository
        }
    }

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos

    suspend fun getUsers() {
        DLog.i("getUsers")
        val users = NetworkHelper.getNetworkService().getUsers()
        _users.postValue(users)
    }

    suspend fun getPhotos() {
        DLog.i("getPhotos")
        val photos = NetworkHelper.getNetworkService().getPhotos()
        _photos.postValue(photos)
    }
}