package com.alikazi.codetest.optus.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alikazi.codetest.optus.database.AppDatabase
import com.alikazi.codetest.optus.utils.DLog

class Repository(private val database: AppDatabase) {

    private lateinit var repository: Repository
    fun getInstance(database: AppDatabase): Repository {
        if (!::repository.isInitialized) {
            repository = Repository(database)
        }
        return repository
    }

    private val _users = MutableLiveData<String>()
    val users: LiveData<String> get() = _users

    private val _photos = MutableLiveData<String>()
    val photos: LiveData<String> get() = _photos

    suspend fun getUsers() {
        DLog.i("getUsers")
        val result = NetworkHelper.getNetworkService().getUsers()
        DLog.d("result $result")
        _users.postValue(result)
    }

    suspend fun getPhotos() {
        DLog.i("getPhotos")
        val result = NetworkHelper.getNetworkService().getPhotos()
        DLog.d("result $result")
        _photos.postValue(result)
    }
}