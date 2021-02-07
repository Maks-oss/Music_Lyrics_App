package com.example.musiclyricsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import song_database.DbAccess

class SongHistoryFragment :Fragment() {
    lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.lyrics_history_fragment,container,false)
        recycler=view.findViewById(R.id.recycler)
        runBlocking {
            val lyricsList=DbAccess.getDatabase().lyricsDao().getLyricsSongs()
            val adapter=SongHistoryAdapter(lyricsList)
            recycler.adapter=adapter
        }
        return view
    }
}