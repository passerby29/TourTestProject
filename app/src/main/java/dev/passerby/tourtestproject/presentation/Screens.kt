@file:OptIn(ExperimentalMaterial3Api::class)

package dev.passerby.tourtestproject.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.passerby.tourtestproject.R
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.Button
import dev.passerby.tourtestproject.domain.models.FunModel
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.domain.models.RoomsModel
import dev.passerby.tourtestproject.domain.models.TourModel
import dev.passerby.tourtestproject.presentation.theme.Grey01
import dev.passerby.tourtestproject.presentation.theme.Grey06
import dev.passerby.tourtestproject.presentation.theme.Grey20
import dev.passerby.tourtestproject.presentation.theme.appFontFamily
import dev.passerby.tourtestproject.presentation.viewmodels.BlogDetailViewModel
import dev.passerby.tourtestproject.presentation.viewmodels.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    mainModel: MainModel,
    homeViewModel: HomeViewModel,
    itemClick: (blogId: Int) -> Unit
) {
    val title = "Home Screen"
    val blogModel = homeViewModel.blogContent.observeAsState().value ?: BlogModel(emptyList())
    val blogContent = blogModel.blogList

    val foodHeader = stringResource(id = R.string.food_header)
    val roomsHeader = stringResource(id = R.string.rooms_header)
    val funHeader = stringResource(id = R.string.fun_header)
    val childHeader = stringResource(id = R.string.child_header)
    val toursHeader = stringResource(id = R.string.tours_header)
    val placesHeader = stringResource(id = R.string.places_header)
    val blogHeader = stringResource(id = R.string.blog_header)

    val foodModel =
        homeViewModel.foodContent.observeAsState().value ?: FunModel(
            emptyList(),
            null,
            false,
            ""
        )
    val foodContent = foodModel.funContent

    val roomModel =
        homeViewModel.roomsContent.observeAsState().value ?: RoomsModel(
            emptyList(),
            null,
            false,
            ""
        )
    val roomContent = roomModel.roomsContent

    val funModel =
        homeViewModel.funContent.observeAsState().value ?: FunModel(
            emptyList(),
            null,
            false,
            ""
        )
    val funContent = funModel.funContent

    val childModel =
        homeViewModel.childContent.observeAsState().value ?: FunModel(
            emptyList(),
            null,
            false,
            ""
        )
    val childContent = childModel.funContent

    val toursModel =
        homeViewModel.toursContent.observeAsState().value ?: TourModel(
            emptyList(),
            null,
            false,
            ""
        )
    val toursContent = toursModel.toursContent

    val placesModel =
        homeViewModel.placeContent.observeAsState().value ?: FunModel(
            emptyList(),
            null,
            false,
            ""
        )
    val placesContent = placesModel.funContent

    Column(
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth()
            .background(Grey01)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        MyTopAppBar(screenTitle = title)
        Buttons(buttons = mainModel.mainInfo.buttons)
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            list(
                header = foodHeader,
                items = foodContent
            ) {
                HomeScreenItemFun(funItem = it)
            }
            list(
                header = roomsHeader,
                items = roomContent
            ) {
                HomeScreenItemRoom(roomItem = it)
            }
            list(
                header = funHeader,
                items = funContent
            ) {
                HomeScreenItemFun(funItem = it)
            }
            list(
                header = childHeader,
                items = childContent
            ) {
                HomeScreenItemFun(funItem = it)
            }
            list(
                header = toursHeader,
                items = toursContent
            ) {
                HomeScreenItemTour(tourItem = it)
            }
            list(
                header = placesHeader,
                items = placesContent
            ) {
                HomeScreenItemFun(funItem = it)
            }
            list(
                header = blogHeader,
                items = blogContent
            ) {
                HomeScreenItemBlog(blogItem = it, itemClick = itemClick)
            }
        })
    }
}

inline fun LazyGridScope.single(crossinline itemContent: @Composable (LazyGridItemScope.() -> Unit)) {
    item(
        span = { GridItemSpan(maxCurrentLineSpan) }
    ) {
        itemContent()
    }
}

inline fun <T> LazyGridScope.list(
    items: List<T>,
    header: String = "",
    isSingleElementInRow: Boolean = false,
    crossinline listContent: @Composable ((T) -> Unit)
) {
    if (header.isNotBlank() && items.isNotEmpty())
        single {
            Text(
                text = header,
                fontSize = 36.sp
            )
        }
    items(
        span = if (isSingleElementInRow) {
            { GridItemSpan(maxCurrentLineSpan) }
        } else null,
        items = items
    ) { item ->
        listContent(item)
    }
}

@Composable
fun Buttons(buttons: List<Button>) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        buttons.forEach {
            ButtonItem(it)
        }
    }
}

@Composable
fun ButtonItem(button: Button) {
    Button(
        modifier = Modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 12.dp),
        onClick = { /*TODO*/ })
    {
        Text(text = button.title)
    }
}

@Composable
fun BlogDetailScreen(
    blogId: Int,
    blogDetailViewModel: BlogDetailViewModel,
    navController: NavController
) {
    blogDetailViewModel.loadBlogDetail(blogId)
    val blogDetail = blogDetailViewModel.blogDetail.observeAsState().value?.blogDetail
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey01)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(
                modifier = Modifier.height(304.dp),
                model = blogDetail?.image?.lg,
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .padding(start = 24.dp, top = 24.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                painter = painterResource(id = R.drawable.ic_circle_back_button),
                contentDescription = null,
            )
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = formatDate(blogDetail?.date),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Grey20
                ),
            )
            Text(
                text = blogDetail?.title.toString(),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Grey20
                )
            )
            Divider(
                modifier = Modifier.padding(vertical = 16.dp), color = Grey06
            )
            Text(
                text = blogDetail?.content.toString(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp
                ),
            )
        }
    }
}

@Composable
fun MapScreen() {
    val title = "Map Screen"
    Scaffold(
        topBar = { MyTopAppBar(title) }
    ) {
        it.calculateBottomPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.teal_700))
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun BookScreen() {
    val title = "Book Screen"
    Scaffold(
        topBar = { MyTopAppBar(title) }
    ) {
        it.calculateBottomPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.teal_700))
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}


@Composable
fun ChatScreen() {
    val title = "Chat Screen"
    Scaffold(
        topBar = { MyTopAppBar(title) }
    ) {
        it.calculateBottomPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.teal_700))
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}


@Composable
fun MoreScreen() {
    val title = "More Screen"
    Scaffold(
        topBar = { MyTopAppBar(title) }
    ) {
        it.calculateBottomPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.teal_700))
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}

private fun formatDate(date: String?): String {
    if (date.isNullOrEmpty()) {
        return date.toString()
    } else {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ITALIAN)
        return formatter.format(parser.parse(date) as Date)
    }
}