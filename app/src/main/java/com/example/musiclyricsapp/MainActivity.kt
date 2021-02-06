package com.example.musiclyricsapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val mainFragment = FragmentFactory.createFragment(MainFragment())
            manager.replace(R.id.frame, mainFragment).commit()
        }
//        findViewById<Button>(R.id.history).setOnClickListener {
//            TODO() //сделать переход между фрагментами
//        }
    }


}