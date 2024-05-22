package ir.atefehtaheri.comicfeed.features.ComicsList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
    val searchText by comicsViewModel.searchText.collectAsState()


    ComicsListScreen(
        uiState = uiState,
        onQueryChange = comicsViewModel::onSearchTextChange,
        searchText = searchText,
    )
}

@Composable
private fun ComicsListScreen(
    uiState: ComicsUiState,
    onQueryChange: (String) -> Unit,
    searchText: String,
) {
        if (uiState.isLoading) {
            LoadingState()
        } else {
            ShowListState(uiState.comicsList,onQueryChange,searchText)
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
    onQueryChange: (String) -> Unit,
    searchText: String,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = searchText,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 15.dp),
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.titleSmall,
            keyboardActions = KeyboardActions(
                onDone = {
                    softwareKeyboardController?.hide()
                }
            ),
            singleLine = true,
            trailingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "",
                )
            },
            placeholder = { Text("Search Text") }
        )
        LazyColumn(modifier = modifier.padding(16.dp), state = listState) {
            items(comicslist) {
                ComicListItem(comicItem = it)
            }
        }
    }
}


