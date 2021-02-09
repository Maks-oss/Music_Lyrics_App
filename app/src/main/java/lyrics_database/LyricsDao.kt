package lyrics_database

import androidx.room.*

@Dao
interface LyricsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLyrics(songLyrics: SongLyrics)

    @Query("SELECT * FROM songLyrics")
    suspend fun getLyricsSongs(): List<SongLyrics>

    @Delete
    suspend fun deleteLyricsSong(songLyrics: SongLyrics)

    @Query("SELECT artistName FROM songLyrics")
    suspend fun getArtistsName(): List<String>

    @Query("SELECT songName FROM songLyrics")
    suspend fun getSongssName(): List<String>
}