package com.alikazi.codetest.optus.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.models.User

@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<Photo>)

    @get:Transaction
    @get:Query("SELECT * FROM Photo")
    val photos: LiveData<List<Photo>>

}