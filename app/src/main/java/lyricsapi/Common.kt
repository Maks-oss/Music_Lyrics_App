package lyricsapi

object Common {
    private const val BASE_URL = "https://api.lyrics.ovh/v1/"
    val retrofitService: LyricsAPI
        get() = RetrofitClient.getClient(BASE_URL).create(LyricsAPI::class.java)
}