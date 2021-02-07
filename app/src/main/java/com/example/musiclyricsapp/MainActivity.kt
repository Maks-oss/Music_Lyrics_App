package com.example.musiclyricsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = FragmentFactory.createFragment(MainFragment())
        supportFragmentManager.beginTransaction().replace(R.id.frame, mainFragment).addToBackStack(null).commit()
    }


}