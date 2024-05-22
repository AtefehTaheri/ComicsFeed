package ir.atefehtaheri.comicfeed.features.ComicsList.UiState

import ir.atefehtaheri.comicfeed.data.ComicsList.remote.model.ComicItem


data class ComicsUiState(
    val isLoading: Boolean = true,
    val comicsList: List<ComicItem> = emptyList()
)
