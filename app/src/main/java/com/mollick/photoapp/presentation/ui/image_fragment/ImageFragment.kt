package com.mollick.photoapp.presentation.ui.image_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mollick.photoapp.R
import com.mollick.photoapp.common.Constants.ADDRESS_EXTRA
import com.mollick.photoapp.common.Constants.ID_EXTRA
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment: Fragment() {
    private lateinit var image: ImageView
    private lateinit var loadingView: View
    private lateinit var imageTitle: TextView
    private lateinit var addressTextView: TextView
    private lateinit var viewModel: ImageDetailViewModel
    private var imageId: Int? = null
    private var address: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imageId = arguments?.getInt(ID_EXTRA)
        address = arguments?.getString(ADDRESS_EXTRA)
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
        viewModel = ViewModelProvider(this)[ImageDetailViewModel::class.java].apply {
            imageId?.let { getImageDetailById(it) } // Initialize on Image fragment creates.
            state.observe(viewLifecycleOwner) { imageDetailState ->
                when {
                    imageDetailState.isLoading -> {
                        loadingView.visibility = View.VISIBLE
                    }

                    imageDetailState.error?.isNotBlank() == true -> {
                        setDescription(imageDetailState.error)
                    }

                    else -> {
                        if (imageDetailState.data != null) {
                            Picasso.with(context).load(imageDetailState.data.url)
                                .fit().centerCrop()
                                .into(image)
                            imageTitle.text = imageDetailState.data.title
                            addressTextView.text = address
                            loadingView.visibility = View.GONE
                        } else {
                            setDescription(getString(R.string.no_data_found))
                        }
                    }
                }
            }
        }
    }

    private fun setupViews(view: View) {
        image = view.findViewById(R.id.detail_image)
        loadingView = view.findViewById(R.id.loading_layout)
        imageTitle = view.findViewById(R.id.image_title)
        addressTextView = view.findViewById(R.id.image_address)
    }

    private fun setDescription(text: String) {
        loadingView.visibility = View.VISIBLE
        loadingView.findViewById<View>(R.id.progress_bar).visibility = View.GONE
        loadingView.findViewById<TextView>(R.id.desc_title).text = text
    }
}
