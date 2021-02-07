package com.example.musiclyricsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.musiclyricsapp.databinding.LyricsFragmentBinding
import com.google.android.material.snackbar.Snackbar
import lyricsapi.Common
import lyricsapi.LyricsAPI
import lyricsapi.SongLyrics
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    private lateinit var binding: LyricsFragmentBinding
    private lateinit var lyricsService: LyricsAPI

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.lyrics_fragment, container, false)

        lyricsService = Common.retrofitService
        binding.search.setOnClickListener {
            getSong()
        }

        binding.history.setOnClickListener {
            fragmentManager?.apply{
                    beginTransaction().replace(R.id.frame, SongHistoryFragment())
                    .addToBackStack(null).commit()
            }
        }
        return binding.getRoot()
    }

    private fun getSong() {
        binding.apply {
            lyricsService.getLyrics(musician.text.toString(), songName.text.toString())
                .enqueue(object : Callback<SongLyrics> {
                    override fun onFailure(call: Call<SongLyrics>, t: Throwable) {
                        Snackbar.make(root, "Что-то не так", Snackbar.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<SongLyrics>,
                        response: Response<SongLyrics>
                    ) {
                        val songLyrics = response.body()
                        if (songLyrics?.lyrics.isNullOrEmpty()) {
                            Snackbar.make(
                                root,
                                "По вашему запросу не было найдено совпадений",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        songText.text = songLyrics?.lyrics
                    }
                })
        }
    }


}