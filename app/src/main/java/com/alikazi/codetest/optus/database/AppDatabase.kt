package com.alikazi.codetest.optus.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.models.User

@TypeConverters(MyTypeConverters::class)
@Database(entities = [User::class, Photo::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao
    abstract fun getPhotosDao(): PhotosDao

    companion object {
        private const val databaseName = "Optus.db"
        private lateinit var INSTANCE: AppDatabase
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        databaseName)
                        .fallbackToDestructiveMigration()
                        .build()

                }
                return INSTANCE
            }
        }
    }


}