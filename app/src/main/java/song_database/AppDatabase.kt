package song_database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(SongLyrics::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lyricsDao(): LyricsDao
}