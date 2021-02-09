package com.example.musiclyricsapp.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import lyrics_database.DbAccess

abstract class ScopeFragment(@LayoutRes id: Int) : Fragment(id) {

    protected val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    protected val db = DbAccess.getDatabase()

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}