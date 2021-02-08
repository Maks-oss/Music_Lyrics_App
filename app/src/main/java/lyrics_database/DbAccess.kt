package lyrics_database

import android.content.Context
import androidx.room.Room

object DbAccess {
    private lateinit var db: AppDatabase
    fun setDatabaseContext(context: Context) {
        db = Room.databaseBuilder(context, AppDatabase::class.java, "songLyrics").build()
    }

    fun getDatabase() = db.lyricsDao()
}