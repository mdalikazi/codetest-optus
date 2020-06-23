package com.alikazi.codetest.optus

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.alikazi.codetest.optus.database.AppDatabase
import com.alikazi.codetest.optus.database.PhotosDao
import com.alikazi.codetest.optus.database.UsersDao
import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.utils.Constants
import com.alikazi.codetest.optus.utils.DLog
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DatabaseTest {

    @get:Rule
    val executor = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var usersDao: UsersDao
    private lateinit var photosDao: PhotosDao

    @Before
    fun openDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        usersDao = database.getUsersDao()
        photosDao = database.getPhotosDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun a_testUsersTable() {
        DLog.i("Start Users table test")
        // Insert mock users
        runBlocking {
            usersDao.insertUsers(getMockUsers())
        }
        // Check data
        usersDao.users.testingObserver {
            assert(it.size == 2)
            assert(it[0].name == "John Doe")
            assert(it[0].address.street == "1 York Street")
            assert(it[1].username == "joedoan")
            assert(it[1].company.name == "XYZ Company")
            DLog.i("Users table test passed")
        }
    }

    @Test
    fun b_testPhotosTable() {
        // Insert mock photos
        runBlocking {
            photosDao.insertPhotos(getMockPhotos())
        }
        // Check data
        photosDao.photos.testingObserver {
            assert(it.size == 2)
            assert(it[0].title == "This is first photo")
            assert(it[0].albumId == 1)
            assert(it[1].title == "This is second photo")
            assert(it[1].url == "https://via.placeholder.com/600/771796")
            DLog.i("Photos test passed")
        }

        runBlocking {
            val photosWithUserId: List<Photo> = photosDao.photosWithUserId(1).map { it }
            assert(photosWithUserId.size == 2)
            assert(photosWithUserId[0].title == "This is first photo")
            assert(photosWithUserId[1].title == "This is second photo")
            DLog.i("PhotosWithUserId test passed")
        }
    }
}