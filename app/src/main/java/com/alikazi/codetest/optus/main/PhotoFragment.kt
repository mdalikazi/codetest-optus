package com.alikazi.codetest.optus.main

import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.utils.Constants
import com.alikazi.codetest.optus.utils.DLog
import com.alikazi.codetest.optus.utils.showImageWithGlide
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment : Fragment() {

    private var photo: Photo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        photo = arguments?.getParcelable(Constants.INTENT_EXTRA_PHOTO_OBJECT)
        DLog.d("photoId ${photo?.id}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        photo?.let {photo ->
            photoImageView.showImageWithGlide(photo.url, photoFragmentProgressBar)
            photoTitle.text = photo.title
        }
    }

}
