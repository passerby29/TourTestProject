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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import dev.passerby.tourtestproject.domain.models.FunModel
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.domain.models.RoomsModel
import dev.passerby.tourtestproject.domain.models.TourModel
import dev.passerby.tourtestproject.presentation.theme.appFontFamily
import dev.passerby.tourtestproject.presentation.viewmodels.BlogDetailViewModel
import dev.passerby.tourtestproject.presentation.viewmodels.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    mainModel: MainModel, homeViewModel: HomeViewModel, itemClick: (blogId: Int) -> Unit
) {
    val blogModel = homeViewModel.blogContent.observeAsState().value ?: BlogModel(emptyList())
    val blogContent = blogModel.blogList

    val foodHeader = stringResource(id = R.string.food_header)
    val roomsHeader = stringResource(id = R.string.rooms_header)
    val funHeader = stringResource(id = R.string.fun_header)
    val childHeader = stringResource(id = R.string.child_header)
    val toursHeader = stringResource(id = R.string.tours_header)
    val placesHeader = stringResource(id = R.string.places_header)
    val blogHeader = stringResource(id = R.string.blog_header)

    val foodModel = homeViewModel.foodContent.observeAsState().value ?: FunModel(
        emptyList(), null, false, ""
    )
    val foodContent = foodModel.funContent

    val roomModel = homeViewModel.roomsContent.observeAsState().value ?: RoomsModel(
        emptyList(), null, false, ""
    )
    val roomContent = roomModel.roomsContent

    val funModel = homeViewModel.funContent.observeAsState().value ?: FunModel(
        emptyList(), null, false, ""
    )
    val funContent = funModel.funContent

    val childModel = homeViewModel.childContent.observeAsState().value ?: FunModel(
        emptyList(), null, false, ""
    )
    val childContent = childModel.funContent

    val toursModel = homeViewModel.toursContent.observeAsState().value ?: TourModel(
        emptyList(), null, false, ""
    )
    val toursContent = toursModel.toursContent

    val placesModel = homeViewModel.placeContent.observeAsState().value ?: FunModel(
        emptyList(), null, false, ""
    )
    val placesContent = placesModel.funContent

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
            columns = GridCells.Fixed(2),
            content = {
                items(1, span = { GridItemSpan(this.maxLineSpan) }) {
                    Buttons(buttons = mainModel.mainInfo.buttons)
                }
                list(blogContent, blogHeader) {
                    HomeScreenItemBlog(blogItem = it, itemClick = itemClick)
                }
                list(foodContent, foodHeader) {
                    HomeScreenItemFun(funItem = it)
                }
                list(roomContent, roomsHeader) {
                    HomeScreenItemRoom(roomItem = it)
                }
                list(funContent, funHeader) {
                    HomeScreenItemFun(funItem = it)
                }
                list(childContent, childHeader) {
                    HomeScreenItemFun(funItem = it)
                }
                list(toursContent, toursHeader) {
                    HomeScreenItemTour(tourItem = it)
                }
                list(placesContent, placesHeader) {
                    HomeScreenItemFun(funItem = it)
                }
            })
    }
}

fun <T> LazyGridScope.list(
    items: List<T>,
    header: String = "",
    listItem: @Composable ((T) -> Unit)
) {
    if (header.isNotBlank() && items.isNotEmpty()) header {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = header,
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Normal,
            )
        )
    }
    items(items.size) {
        listItem(items[it])
    }
}

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

@Composable
fun Buttons(buttons: List<MainModel.MainInfo.Button>) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        buttons.forEach {
            ButtonItem(it)
        }
    }
}

@Composable
fun ButtonItem(button: MainModel.MainInfo.Button) {
    Button(modifier = Modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 12.dp),
        onClick = { /*TODO*/ }) {
        Text(text = button.title)
    }
}

@Composable
fun BlogDetailScreen(
    blogId: Int, blogDetailViewModel: BlogDetailViewModel, navController: NavController
) {
    blogDetailViewModel.loadBlogDetail(blogId)
    val blogDetail = blogDetailViewModel.blogDetail.observeAsState().value?.blogDetail
    Column(
        modifier = Modifier
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
                ),
            )
            Text(
                text = blogDetail?.title.toString(), style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Bold,
                )
            )
            Divider(
                modifier = Modifier.padding(vertical = 16.dp)
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
    val title = "Экран карта"
    Scaffold {
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
    val title = "Экран бронь"
    Scaffold {
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
    val title = "Экран чат"
    Scaffold {
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
    val title = "Экран  ещё"
    Scaffold {
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
    return if (date.isNullOrEmpty()) {
        date.toString()
    } else {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ITALIAN)
        formatter.format(parser.parse(date) as Date)
    }
}