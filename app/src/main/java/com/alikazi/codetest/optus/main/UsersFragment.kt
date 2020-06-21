package com.alikazi.codetest.optus.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.utils.Constants
import com.alikazi.codetest.optus.utils.DLog
import com.alikazi.codetest.optus.utils.Injector
import com.alikazi.codetest.optus.utils.showSnackbar
import com.alikazi.codetest.optus.viewmodels.UsersViewModel
import kotlinx.android.synthetic.main.fragment_users.*
import java.net.UnknownHostException

@Suppress("DEPRECATION")
class UsersFragment : Fragment(), UsersRecyclerAdapter.OnUserItemClickListener {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMyViewModel()
    }

    private fun initMyViewModel() {
        /**
         * Even though [androidx.lifecycle.ViewModelProviders] is deprecated, it must be used
         * because using [ViewModelProvider] takes activity context which
         * registers the ViewModel to both activity & fragment and
         * causes an extra trigger on observed LiveData.
         */
        // Using full class name to avoid deprecated warning in import
        usersViewModel = androidx.lifecycle.ViewModelProviders.of(
            this,
            Injector.provideMyViewModelFactory(activity!!))
            .get(UsersViewModel::class.java)

        usersViewModel.users.observe(this, Observer {
            if (it.isEmpty()) {
                DLog.d("We have no data")
                usersViewModel.getUsersAndPhotos()
            } else {
                DLog.d("users")
                usersRecyclerAdapter.submitList(it)
            }
        })

        usersViewModel.photos.observe(this, Observer {
            it?.let {
                DLog.d("photos")
            }
        })

        usersViewModel.isLoading.observe(this, Observer {
            processVisibility(usersFragmentProgressBar, it)
        })

        usersViewModel.errors.observe(this, Observer {
            it?.let {
                if (it is UnknownHostException) {
                    usersFragmentContainer.showSnackbar(getString(R.string.users_fragment_snackbar_message_offline))
                } else {
                    usersFragmentContainer.showSnackbar(it.toString())
                }
                DLog.d("error: $it")
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        onBackPressedInFragment()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        usersRecyclerAdapter = UsersRecyclerAdapter(activity, this)
        recyclerViewUsers.adapter = usersRecyclerAdapter
    }

    override fun onUserClicked(userId: Int) {
        DLog.d("userId $userId")
        goToAlbumFragment(userId)
    }

    private fun goToAlbumFragment(userId: Int) {
        val fragment = AlbumFragment()
        val args = Bundle()
        args.putInt(Constants.INTENT_EXTRA_USER_ID, userId)
        fragment.arguments = args
        childFragmentManager.beginTransaction()
            .replace(R.id.usersChildFragmentContainer, fragment)
            .addToBackStack(AlbumFragment::class.java.simpleName)
            .commit()
    }

    private fun processVisibility(view: View, shouldShow: Boolean) {
        view.visibility = if (shouldShow) View.VISIBLE else View.GONE
    }
}
