package com.alikazi.codetest.optus.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.databinding.FragmentUsersBinding
import com.alikazi.codetest.optus.utils.*
import com.alikazi.codetest.optus.viewmodels.UsersViewModel
import kotlinx.android.synthetic.main.fragment_users.*
import java.net.UnknownHostException

@Suppress("DEPRECATION")
class UsersFragment : Fragment(), UsersRecyclerAdapter.OnUserItemClickListener {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUsersViewModel()
        usersRecyclerAdapter = UsersRecyclerAdapter(activity, this)
    }

    private fun initUsersViewModel() {
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
                usersRecyclerAdapter.submitList(it)
            }
        })

        usersViewModel.errors.observe(this, Observer {
            it?.let {
                if (it is UnknownHostException) {
                    usersFragmentContainer.showSnackbar(getString(R.string.snackbar_message_no_internet))
                } else {
                    usersFragmentContainer.showSnackbar(getString(R.string.snackbar_message_generic_error))
                }
                DLog.d("error: $it")
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentUsersBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_users, container, false)
        binding.apply {
            usersAdapter = usersRecyclerAdapter
            viewModel = usersViewModel
            lifecycleOwner = this@UsersFragment
        }
        return binding.root
    }

    override fun onUserClicked(userId: Int) {
        DLog.d("userId $userId")
        (activity as MainActivity).goToAlbumFragment(userId)
    }

}
