package com.mollick.photoapp.presentation.ui.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mollick.photoapp.R
import com.mollick.photoapp.common.Constants.ADDRESS_EXTRA
import com.mollick.photoapp.common.Constants.ID_EXTRA
import com.mollick.photoapp.presentation.ui.image_fragment.ImageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListFragment : Fragment(), UserListAdapter.ItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingView: View
    private lateinit var viewModel: UserListViewModel
    private lateinit var adapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        viewModel = ViewModelProvider(this)[UserListViewModel::class.java].apply {
            state.observe(viewLifecycleOwner) { state ->
                when  {
                    state.isLoading -> {
                        loadingView.visibility = View.VISIBLE
                    }
                    state.error?.isNotBlank() == true -> {
                        setDescription(state.error)
                    }
                    else -> {
                        if (state.data.isNotEmpty()) {
                            adapter = UserListAdapter(state.data, this@UsersListFragment)
                            recyclerView.adapter = adapter
                            recyclerView.layoutManager = LinearLayoutManager(context)
                            loadingView.visibility = View.GONE
                        } else {
                            setDescription(getString(R.string.no_data_found))
                        }
                    }
                }
            }
        }
    }

    override fun onItemClicked(id: Int) {
        val fragment = ImageFragment()
        val bundle = Bundle()
        bundle.putInt(ID_EXTRA, id)
        bundle.putString(
            ADDRESS_EXTRA,
            adapter.userList.find { it.id == id}?.address.toString()
        )
        fragment.arguments = bundle

        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupViews(view: View) {
        recyclerView = view.findViewById(R.id.user_list_recycler_view)
        loadingView = view.findViewById(R.id.loading_layout)
    }

    private fun setDescription(text: String) {
        loadingView.visibility = View.VISIBLE
        loadingView.findViewById<View>(R.id.progress_bar).visibility = View.GONE
        loadingView.findViewById<TextView>(R.id.desc_title).text = text
    }
}
