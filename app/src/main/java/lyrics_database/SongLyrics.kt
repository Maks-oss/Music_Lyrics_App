package lyrics_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SongLyrics(
    @ColumnInfo(name = "songText")val lyrics: String,
    @ColumnInfo(name = "songName")val song: String,
    @ColumnInfo(name = "artistName")val artist: String
){
    @PrimaryKey(autoGenerate = true) var id:Int=0
}