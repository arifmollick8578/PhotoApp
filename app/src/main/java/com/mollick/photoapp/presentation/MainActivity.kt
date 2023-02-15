package com.mollick.photoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mollick.photoapp.R
import com.mollick.photoapp.presentation.ui.user_list.UsersListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment_container, UsersListFragment())
            .commit()
    }
}
