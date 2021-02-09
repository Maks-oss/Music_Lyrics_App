package com.example.musiclyricsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.databinding.DataBindingUtil
import com.example.musiclyricsapp.core.ScopeFragment
import com.example.musiclyricsapp.databinding.LyricsFragmentBinding
import com.example.musiclyricsapp.navigation.Navigator
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import lyrics_api.Common
import lyrics_api.LyricsAPI
import lyrics_database.SongLyrics
import kotlin.properties.Delegates

class MainFragment : ScopeFragment(R.layout.lyrics_fragment) {

    var navigator: Navigator by Delegates.notNull()

    private lateinit var binding: LyricsFragmentBinding
    private val lyricsService: LyricsAPI = Common.retrofitService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.lyrics_fragment, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArtistNameSuggestion()
        getSongNameSuggestion()

        binding.search.setOnClickListener { getSong() }
        binding.history.setOnClickListener {
            navigator.requestNavigation(SongHistoryFragment())
        }
    }

    private fun getArtistNameSuggestion() {
        scope.launch {
            val list = db.getArtistsName().toTypedArray() //io
            applySuggestionToView(binding.musician, list)
        }
    }

    private fun getSongNameSuggestion() {
        scope.launch {
            val list = db.getSongssName().toTypedArray()
            applySuggestionToView(binding.songName, list)
        }
    }

    private suspend fun applySuggestionToView(
        view: AppCompatAutoCompleteTextView,
        list: Array<String>
    ) {
        toMain {
            view.setAdapter(
                ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    list
                )
            )
        }
    }

    private fun getSong() {
        val artist = binding.musician.text.toString()
        val song = binding.songName.text.toString()

        scope.launch {
            try {
                val songLyrics: SongLyrics = lyricsService.getLyrics(artist, song)
                if (songLyrics.lyrics.isEmpty()) {
                    showSnack("По вашему запросу не было найдено совпадений")
                } else {
                    toMain { binding.songText.text = songLyrics.lyrics }
                    db.insertLyrics(SongLyrics(songLyrics.lyrics, song, artist))
                }
            } catch (exp: Exception) {
                showSnack("Что-то не так")
            }
        }
    }

    private fun showSnack(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}