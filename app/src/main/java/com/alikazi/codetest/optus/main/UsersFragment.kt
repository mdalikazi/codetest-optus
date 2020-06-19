package com.alikazi.codetest.optus.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alikazi.codetest.optus.R

class UsersFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        onBackPressedInFragment()
    }

    private fun goToAlbumFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.usersFragmentContainer, AlbumFragment())
            .addToBackStack(AlbumFragment::class.java.simpleName)
            .commit()
    }

}
