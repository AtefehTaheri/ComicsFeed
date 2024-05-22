package ir.atefehtaheri.comicfeed.features.ComicsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.comicfeed.data.ComicsList.repository.ComicRepository
import ir.atefehtaheri.comicfeed.features.ComicsList.UiState.ComicsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val comicRepository: ComicRepository
) : ViewModel() {


    private var _uiState = MutableStateFlow<ComicsUiState>(ComicsUiState())
    val uiState: StateFlow<ComicsUiState> = _uiState

        init {
             loadComicList()
        }

        fun loadComicList() {
            viewModelScope.launch {
                comicRepository.getAllComics().collect { comicList ->
                    _uiState.update {
                        ComicsUiState(false, comicList)
                    }
                }
            }
        }



}