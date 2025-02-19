package com.alikazi.codetest.optus.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.utils.Constants
import com.facebook.stetho.Stetho

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Stetho.initializeWithDefaults(this)
        if (savedInstanceState == null) {
            goToUsersFragment()
        }
    }

    private fun goToUsersFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainActivityFragmentContainer, UsersFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    fun goToAlbumFragment(userId: Int) {
        val fragment = AlbumFragment()
        val args = Bundle()
        args.putInt(Constants.INTENT_EXTRA_USER_ID, userId)
        fragment.arguments = args
        goToChildFragment(fragment)
    }

    fun goToPhotoFragment(photo: Photo) {
        val fragment = PhotoFragment()
        val args = Bundle()
        args.putParcelable(Constants.INTENT_EXTRA_PHOTO_OBJECT, photo)
        fragment.arguments = args
        goToChildFragment(fragment)
    }

    private fun goToChildFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_left, 0, 0, R.anim.slide_out_right)
            .replace(R.id.mainActivityFragmentContainer, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }

}