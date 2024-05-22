package ir.atefehtaheri.comicfeed.data.ComicsList.remote.model

import android.net.Uri

data class ComicItem(
    val title: String,
    val transcript: String,
    val imgUri: Uri,
    val year: Int,
    val month: Int,
    val day: Int
)