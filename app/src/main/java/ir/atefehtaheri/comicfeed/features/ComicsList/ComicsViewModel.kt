package ir.atefehtaheri.comicfeed.features.ComicsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.atefehtaheri.comicfeed.data.ComicsList.repository.ComicRepository
import ir.atefehtaheri.comicfeed.features.ComicsList.UiState.ComicsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val comicRepository: ComicRepository
) : ViewModel() {


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private var _uiState = MutableStateFlow<ComicsUiState>(ComicsUiState())
    val uiState: StateFlow<ComicsUiState> =  searchText
        .combine(_uiState) { searchText, comicsUiState ->
            if (searchText.isBlank()) {
                comicsUiState
            }
            val filteredList= comicsUiState.comicsList.filter {
                it.title.uppercase().contains(searchText.trim().uppercase()) ||
                        it.transcript.uppercase().contains(searchText.trim().uppercase())
            }.sortedWith (compareBy({ it.year }, { it.month },{ it.day }))
            comicsUiState.copy(comicsList = filteredList)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _uiState.value
        )

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
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }


}