package com.example.musiclyricsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.musiclyricsapp.navigation.Navigator
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), Navigator {

    private var mainFragment: MainFragment by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val previous = supportFragmentManager.findFragmentById(R.id.frame) ?: MainFragment()
        mainFragment = previous as MainFragment
        mainFragment.navigator = this

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, previous)
            .commit()
    }

    override fun requestNavigation(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }
}