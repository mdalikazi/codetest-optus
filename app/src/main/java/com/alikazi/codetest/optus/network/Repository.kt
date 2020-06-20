package com.alikazi.codetest.optus.network

import androidx.lifecycle.map
import com.alikazi.codetest.optus.database.AppDatabase
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
    val photosFromDb = database.getPhotosDao().photos.map { it }

    suspend fun getUsers() {
        DLog.i("getUsers")
        val users = NetworkHelper.getNetworkService().getUsers()
        database.getUsersDao().insertUsers(users)
    }

    suspend fun getPhotos() {
        DLog.i("getPhotos")
        val photos = NetworkHelper.getNetworkService().getPhotos()
        database.getPhotosDao().insertPhotos(photos)
    }
}