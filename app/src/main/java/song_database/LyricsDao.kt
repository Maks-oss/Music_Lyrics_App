package song_database

import androidx.room.*

@Dao
interface LyricsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLyrics(songLyrics: SongLyrics)
    @Query("SELECT * FROM songLyrics")
    suspend fun getLyricsSongs():List<SongLyrics>
    @Delete
    suspend fun deleteLyricsSong(songLyrics: SongLyrics)
}