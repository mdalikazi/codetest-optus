package com.alikazi.codetest.optus.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.databinding.FragmentPhotoBinding
import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.utils.Constants
import com.alikazi.codetest.optus.utils.DLog

class PhotoFragment : Fragment() {

    private var photo: Photo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        photo = arguments?.getParcelable(Constants.INTENT_EXTRA_PHOTO_OBJECT)
        DLog.d("photoId ${photo?.id}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPhotoBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_photo, container, false)
        binding.photo = photo
        return binding.root
    }

}
