package com.example.musiclyricsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import lyricsapi.SongLyrics

class SongHistoryFragment :Fragment() {
    lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.lyrics_history_fragment,container,false)
        recycler=view.findViewById(R.id.recycler)
        val adapter=SongHistoryAdapter(listOf(SongLyrics("Some song text ","Some song name","Some artist name"),SongLyrics("Some song text","Some song name","Some artist name")))
        recycler.adapter=adapter

        return view
    }
}