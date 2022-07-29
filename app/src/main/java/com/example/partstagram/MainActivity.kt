package com.example.partstagram

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.partstagram.fragments.ComposeFragment
import com.example.partstagram.fragments.FeedFragment
import com.example.partstagram.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener { item ->

            var fragmentToShow: Fragment? = null
            when (item.itemId) {

                R.id.action_compose -> {
                    fragmentToShow = ComposeFragment()
                }
                R.id.action_home -> {
                    fragmentToShow = FeedFragment()
                }
                R.id.action_profile -> {
                    fragmentToShow = ProfileFragment()
                }
            }

            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.rlContainer, fragmentToShow)
                    .commit()
            }
            true
        }

        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .selectedItemId = R.id.action_home



    }


    companion object {
        const val TAG = "MainActivity"
    }
}