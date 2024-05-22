package ir.atefehtaheri.comicfeed.features.ComicsList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.atefehtaheri.comicfeed.data.ComicsList.remote.model.ComicItem
import ir.atefehtaheri.comicfeed.features.ComicsList.UiState.ComicsUiState


@Composable
internal fun ComicsListRoute(
    modifier: Modifier = Modifier,
    comicsViewModel: ComicsViewModel = hiltViewModel(),
) {

    val uiState by comicsViewModel.uiState.collectAsState()

    ComicsListScreen(
        uiState = uiState
    )
}

@Composable
private fun ComicsListScreen(
    uiState: ComicsUiState,
) {
        if (uiState.isLoading) {
            LoadingState()
        } else {
            ShowListState(uiState.comicsList)
        }
}


@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(color = Color.Black)
        Text(
            modifier = Modifier.padding(8.dp), text = "Loading"
        )
    }
}


@Composable
private fun ShowListState(
    comicslist: List<ComicItem>,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


    LazyColumn(modifier = modifier.padding(16.dp), state = listState) {
        items(comicslist){
            ComicListItem(comicItem = it)
        }
    }
}}


