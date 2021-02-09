package com.example.musiclyricsapp

import android.app.Application
import lyrics_database.DbAccess

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        DbAccess.setDatabaseContext(this)
    }
}