package com.example.musiclyricsapp

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclyricsapp.core.ScopeFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lyrics_database.SongLyrics
import kotlin.properties.Delegates

class SongHistoryFragment : ScopeFragment(R.layout.lyrics_history_fragment) {

    private var recycler: RecyclerView by Delegates.notNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler)

        scope.launch {
            val lyricsList = db.getLyricsSongs()
            val adapter = SongHistoryAdapter(lyricsList as ArrayList<SongLyrics>)
            withContext(Dispatchers.Main) {
                recycler.adapter = adapter
            }
        }
    }
}