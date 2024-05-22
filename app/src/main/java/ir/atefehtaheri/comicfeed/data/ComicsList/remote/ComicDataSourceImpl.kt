package ir.atefehtaheri.comicfeed.data.ComicsList.remote


import android.net.Uri
import ir.atefehtaheri.comicfeed.data.ComicsList.remote.model.ComicItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ComicDataSourceImpl @Inject constructor(): ComicDataSource {
    private val comicList = listOf(
        ComicItem(
            title = "Snopes",
            transcript = "Another urban legend? You should check out Snopes before sending me this stuff.\nOops; yeah.\nMan, Snopes is really great--independent fact-checkers trawling our collective discourse, filtering out misinformation.\nYeah, but they have their dark side. The couple that runs snopes.com also runs a network of spam servers that start many of those forwarded stories in the first place, ensuring they'll always have business.\nThat's absurd. Plus, it's definitely not true--it was debunked by...\nYes?\n... Oh my God.",
            imgUri = Uri.parse("https://imgs.xkcd.com/comics/snopes.png"),
            year = 2022,
            month = 6,
            day = 2,
        ),
        ComicItem(
            title = "Pendulum Types",
            transcript = "Pendulum types",
            imgUri = Uri.parse("https://imgs.xkcd.com/comics/pendulum_types.png"),
            year = 2024,
            month = 5,
            day = 5
        ),
        ComicItem(
            title = "xkcd Phone 2000",
            transcript = "Our retina display features hundreds of pixels per inch in the central fovea region.",
            imgUri = Uri.parse("https://imgs.xkcd.com/comics/xkcd_phone_2000.png"),
            year = 2024,
            month = 4,
            day = 12
        ),
        ComicItem(
            title = "Premiere",
            transcript = "Standard vacuous entertainment newscast. An anchor starts off the segment with an inset feed of a field reporter\nIn-studio News Anchor: All Hollywood is in town or tonight's star-studded premier! We go live to our reporter on the red carpet. How do things look?\n\nField reporter feed switches to fullscreen. The reporter is stating on the red carpet in front of a full crowd.\nField reporter: Bleak. In 800 million years, the aging, brightening sun will boil away the oceans, and all this will be blowing sand.\n\nSwitch back to initial framing\nAnchor: Oh. Um.  ..sounds pretty grim.  How are the stars reacting?\nReporter: Hydrogen fusion. But it won't last forever.\nAnchor. I mean the *movie* stars.\nReporter: They won't last forever either. None of us will.",
            imgUri = Uri.parse("https://imgs.xkcd.com/comics/premiere.png"),
            year = 2024,
            month = 1,
            day = 11
        ),
        ComicItem(
            title = "1000 Comics",
            transcript = "1000 characters, numerous of which have appeared previously in other comics, are arranged to create the number \"1000\". Two more people stand in the foreground commenting on the formation\n\nPerson 1: WOOOO!\nPerson 2: Wow - Just 24 to go until a big round-number milestone!",
            imgUri = Uri.parse("https://imgs.xkcd.com/comics/1000_comics.png"),
            year = 2023,
            month = 3,
            day = 29

        ),
        ComicItem(
            title = "Scary Triangles",
            transcript = "Some triangles and stuff",
            imgUri = Uri.parse("https://imgs.xkcd.com/comics/scary_triangles.png"),
            year = 2023,
            month = 1,
            day = 15

        ),
        ComicItem(
            title = "Call My Cell",
            transcript = "Hey, can you call my cell?' '...I'm trying, but it says this number is blocked?' 'Ok, thanks, just checking.",
            imgUri = Uri.parse("https://imgs.xkcd.com/comics/call_my_cell.png"),
            year = 2024,
            month = 2,
            day = 13
        )
    )
    override fun fetchAllComics(): Flow<List<ComicItem>> = flow {
        // Delay to simulate the data getting loaded from network
        delay(700)
        emit(comicList)
    }
}