package lyrics_api

import lyrics_database.SongLyrics
import retrofit2.http.GET
import retrofit2.http.Path

interface LyricsAPI {
    @GET("{artist}/{song}")
    suspend fun getLyrics(@Path("artist") artist: String, @Path("song") song: String): SongLyrics
}