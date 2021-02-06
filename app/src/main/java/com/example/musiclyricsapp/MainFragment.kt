package com.example.musiclyricsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import lyricsapi.Common
import lyricsapi.LyricsAPI
import lyricsapi.SongLyrics
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    private lateinit var lyricsService: LyricsAPI

    private lateinit var artistSearchField: TextInputEditText
    private lateinit var songSearchField: TextInputEditText
    private lateinit var songText: MaterialTextView
    private lateinit var buttonSearch: Button
    private lateinit var layout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.lyrics_fragment, container, false)
        initViews(inflate)
        lyricsService = Common.retrofitService
        buttonSearch.setOnClickListener {
            getSong()
        }
        return inflate
    }

    private fun initViews(container: View) {

        artistSearchField = container.findViewById(R.id.musician)
        songSearchField = container.findViewById(R.id.song_name)
        songText = container.findViewById(R.id.song_text)
        buttonSearch = container.findViewById(R.id.search)
        layout=container.findViewById(R.id.root)
    }

    private fun getSong() {
        lyricsService.getLyrics(artistSearchField.text.toString(), songSearchField.text.toString())
            .enqueue(object : Callback<SongLyrics> {
                override fun onFailure(call: Call<SongLyrics>, t: Throwable) {
                    Snackbar.make(layout,"Что-то не так",Snackbar.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<SongLyrics>, response: Response<SongLyrics>) {
                    val songLyrics=response.body()
                    if (songLyrics?.lyrics.isNullOrEmpty()){
                        Snackbar.make(layout,"По вашему запросу не было найдено совпадений",Snackbar.LENGTH_LONG).show()
                    }
                    songText.text =songLyrics?.lyrics
                }
            })

    }
}