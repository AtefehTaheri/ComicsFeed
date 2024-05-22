package ir.atefehtaheri.comicfeed.data.ComicsList.remote

import ir.atefehtaheri.comicfeed.data.ComicsList.remote.model.ComicItem
import kotlinx.coroutines.flow.Flow

interface ComicDataSource {
    fun fetchAllComics(): Flow<List<ComicItem>>
}