package ir.atefehtaheri.comicfeed.data.ComicsList.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.comicfeed.data.ComicsList.repository.ComicRepository
import ir.atefehtaheri.comicfeed.data.ComicsList.repository.ComicRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ComicRepositoryModule {

    @Binds
    @Singleton
    fun getComicRepository(
        comicRepositoryImpl: ComicRepositoryImpl
    ): ComicRepository

}