package lyricsapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import song_database.SongLyrics

interface LyricsAPI {
    @GET("{artist}/{song}")
    fun getLyrics(@Path("artist") artist:String,@Path("song") song:String): Call<SongLyrics>
}