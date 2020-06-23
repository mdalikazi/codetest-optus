package com.alikazi.codetest.optus.network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.alikazi.codetest.optus.database.AppDatabase
import com.alikazi.codetest.optus.models.Photo
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

    val usersFromDb = database.getUsersDao().users.map { it }

    private val _photosWithUserId = MutableLiveData<List<Photo>>()
    val photosWithUserId get() = _photosWithUserId

    suspend fun getUsersFromNetwork() {
        DLog.i("getUsersFromNetwork")
        val users = NetworkHelper.getNetworkService().getUsers()
        database.getUsersDao().insertUsers(users)
    }

    suspend fun getPhotosFromNetwork() {
        DLog.i("getPhotosFromNetwork")
        val photos = NetworkHelper.getNetworkService().getPhotos()
        database.getPhotosDao().insertPhotos(photos)
    }

    suspend fun getAlbumWithUserId(userId: Int) {
        DLog.i("getAlbumWithUserId")
        _photosWithUserId.postValue(database.getPhotosDao().photosWithUserId(userId))
    }

}