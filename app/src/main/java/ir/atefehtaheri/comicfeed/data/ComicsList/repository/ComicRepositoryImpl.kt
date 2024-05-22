package ir.atefehtaheri.comicfeed.data.ComicsList.repository

import ir.atefehtaheri.comicfeed.data.ComicsList.remote.ComicDataSource
import ir.atefehtaheri.comicfeed.data.ComicsList.remote.model.ComicItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val comicDataSource: ComicDataSource
) : ComicRepository {

    override fun getAllComics(): Flow<List<ComicItem>> = comicDataSource.fetchAllComics()
}