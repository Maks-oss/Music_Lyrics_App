package song_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LyricsDao {
    @Insert
    suspend fun insertLyrics(songLyrics: SongLyrics)
    @Query("SELECT * FROM songLyrics")
    suspend fun getLyricsSongs():List<SongLyrics>
}