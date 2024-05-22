package ir.atefehtaheri.comicfeed.features.ComicsList


import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.atefehtaheri.comicfeed.data.ComicsList.remote.model.ComicItem
import ir.atefehtaheri.myapplication.R

@Composable
fun ComicListItem(
    comicItem: ComicItem,
    modifier: Modifier = Modifier,

    ) {

    var expandedState by remember { mutableStateOf(false) }
    var likeState by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        likeState = !likeState
                    },
                    onTap = { expandedState = !expandedState }
                )
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(comicItem.imgUri)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
                fallback = painterResource(R.drawable.placeholder),
                contentDescription = "",
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.FillBounds,
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = comicItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                )
                Row {
                    if (likeState) {
                        Image(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(color = Color.Red),
                        )
                    } else {
                        Image(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(color = Color.White),
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    shareTextButton(LocalContext.current, comicItem.transcript)
                }
            }
        }
        if (expandedState) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = comicItem.transcript,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
                maxLines = 9,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
            Text(
                modifier = modifier.align(Alignment.CenterHorizontally),
                text = "${comicItem.month}/${comicItem.day}/${comicItem.year}",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )
            Spacer(Modifier.height(8.dp))
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = comicItem.transcript,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}


@Composable
fun shareTextButton(context: Context, text: String) {

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent,"Share Text")
    Image(
        modifier = Modifier.clickable {
            ContextCompat.startActivity(context, shareIntent, null)
        },
        imageVector = Icons.Default.Share,
        contentDescription = "",
        colorFilter = ColorFilter.tint(color = Color.Black),
    )

}