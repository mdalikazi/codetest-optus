package com.alikazi.codetest.optus

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.alikazi.codetest.optus.database.AppDatabase
import com.alikazi.codetest.optus.database.PhotosDao
import com.alikazi.codetest.optus.database.UsersDao
import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.utils.DLog
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DatabaseTest {

    private lateinit var database: AppDatabase
    private lateinit var usersDao: UsersDao
    private lateinit var photosDao: PhotosDao

    @get:Rule
    val executor = InstantTaskExecutorRule()

    @Before
    fun openDatabase() {
        val context:Application = ApplicationProvider.getApplicationContext()
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
        DLog.i("Start User table test")
        // Insert mock users
        runBlocking {
            usersDao.insertUsers(getMockUsers())
        }
        // Check data
        usersDao.users.testingObserver {
            assertThat(it.size, `is`(2))
            assertThat(it[0].name, `is`("John Doe"))
            assertThat(it[0].address.street, `is`(getMockAddress1().street))
            assertThat(it[0].company.name, `is`(getMockCompany1().name))
            assertThat(it[1].username, `is`("joedoan"))
            assertThat(it[1].address.street, `is`(getMockAddress2().street))
            assertThat(it[1].company.name, `is`(getMockCompany2().name))
            DLog.i("Users table test passed")
        }
    }

    @Test
    fun b_testPhotosTable() {
        DLog.i("Start Photo table test")
        // Insert mock photos
        runBlocking {
            photosDao.insertPhotos(getMockPhotos())
        }
        // Check data
        photosDao.photos.testingObserver {
            assertThat(it.size, `is`(2))
            assertThat(it[0].title, `is`("This is first photo"))
            assertThat(it[0].albumId, `is`(1))
            assertThat(it[1].title, `is`("This is second photo"))
            assertThat(it[1].url, `is`("https://via.placeholder.com/600/771796"))
            DLog.i("Photos test passed")
        }

        runBlocking {
            val photosWithUserId: List<Photo> = photosDao.photosWithUserId(1).map { it }
            assertThat(photosWithUserId.size, `is`(2))
            assertThat(photosWithUserId[0].title, `is`("This is first photo"))
            assertThat(photosWithUserId[1].title, `is`("This is second photo"))
            DLog.i("PhotosWithUserId test passed")
        }
    }
}