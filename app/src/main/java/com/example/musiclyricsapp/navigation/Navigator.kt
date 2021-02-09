package com.example.musiclyricsapp.navigation

import androidx.fragment.app.Fragment

interface Navigator {
    fun requestNavigation(fragment: Fragment)
}