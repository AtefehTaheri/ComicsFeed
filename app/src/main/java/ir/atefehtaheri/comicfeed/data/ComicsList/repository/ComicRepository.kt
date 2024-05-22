package ir.atefehtaheri.comicfeed.data.ComicsList.repository


import ir.atefehtaheri.comicfeed.data.ComicsList.remote.model.ComicItem
import kotlinx.coroutines.flow.Flow

interface ComicRepository {
    fun getAllComics(): Flow<List<ComicItem>>

}