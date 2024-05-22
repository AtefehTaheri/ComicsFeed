package ir.atefehtaheri.comicfeed.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ir.atefehtaheri.comicfeed.features.ComicsList.ComicsListRoute


const val comicListRoute = "comicListroute"

fun NavGraphBuilder.comicListDestination(){

    composable(route = comicListRoute) {
        ComicsListRoute()
    }
}

