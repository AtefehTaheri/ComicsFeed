package ir.atefehtaheri.comicfeed.data.ComicsList.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.atefehtaheri.comicfeed.data.ComicsList.remote.ComicDataSource
import ir.atefehtaheri.comicfeed.data.ComicsList.remote.ComicDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ComicDataSourceModule {

    @Binds
    @Singleton
    fun getComicDataSource(
        comicDataSourceImpl: ComicDataSourceImpl
    ): ComicDataSource

}