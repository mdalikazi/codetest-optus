package com.alikazi.codetest.optus.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.utils.*
import com.alikazi.codetest.optus.viewmodels.AlbumViewModel
import kotlinx.android.synthetic.main.fragment_album.*
import java.net.UnknownHostException

@Suppress("DEPRECATION")
class AlbumFragment : Fragment() {

    private var userId = -1
    private lateinit var albumViewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = arguments?.getInt(Constants.INTENT_EXTRA_USER_ID) ?: -1
        DLog.d("userId $userId")

        initPhotosViewModel()
        initAlbumbsRecyclerView()
    }

    private fun initAlbumbsRecyclerView() {

    }

    private fun initPhotosViewModel() {
        albumViewModel = androidx.lifecycle.ViewModelProviders.of(
            this,
            Injector.provideMyViewModelFactory(activity!!))
            .get(AlbumViewModel::class.java)

        albumViewModel.photos.observe(this, Observer {
            it?.let {
                DLog.d("photos size: ${it.size}")
            }
        })

        albumViewModel.isLoading.observe(this, Observer {
            albumFragmentProgressBar.processVisibility(it)
        })

        albumViewModel.errors.observe(this, Observer {
            it?.let {
                if (it is UnknownHostException) {
                    albumFragmentContainer.showSnackbar(getString(R.string.snackbar_message_no_internet))
                } else {
                    albumFragmentContainer.showSnackbar(getString(R.string.snackbar_message_generic_error))
                }
                DLog.d("error: $it")
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        onBackPressedInFragment()
        albumViewModel.getAlbumWithUserId(userId)
    }

    private fun goToPhotoFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.usersFragmentContainer, PhotoFragment())
            .addToBackStack(PhotoFragment::class.java.simpleName)
            .commit()
    }



}
