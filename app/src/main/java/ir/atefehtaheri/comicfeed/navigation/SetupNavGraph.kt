package ir.atefehtaheri.comicfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String = comicListRoute) {


    NavHost(navController = navController, startDestination = startDestination) {

        comicListDestination()

    }


}