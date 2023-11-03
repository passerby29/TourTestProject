package dev.passerby.tourtestproject.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.passerby.tourtestproject.R
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.FunModel
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.domain.models.RoomsModel
import dev.passerby.tourtestproject.domain.models.TourModel
import dev.passerby.tourtestproject.presentation.Buttons
import dev.passerby.tourtestproject.presentation.HomeScreenItemBlog
import dev.passerby.tourtestproject.presentation.HomeScreenItemFun
import dev.passerby.tourtestproject.presentation.HomeScreenItemRoom
import dev.passerby.tourtestproject.presentation.HomeScreenItemTour
import dev.passerby.tourtestproject.presentation.list
import dev.passerby.tourtestproject.presentation.viewmodels.HomeViewModel

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