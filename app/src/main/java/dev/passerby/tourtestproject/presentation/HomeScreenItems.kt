package dev.passerby.tourtestproject.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.passerby.tourtestproject.R
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.FunModel
import dev.passerby.tourtestproject.domain.models.RoomsModel
import dev.passerby.tourtestproject.domain.models.TourModel
import dev.passerby.tourtestproject.presentation.theme.appFontFamily

@Composable
fun HomeScreenItemFun(funItem: FunModel.FunItem) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp)) {
            AsyncImage(
                model = funItem.image.md,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.image_placeholder)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = funItem.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,

                )
        )
        Text(
            text = funItem.subtitle,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Normal,

                )
        )
    }
}

@Composable
fun HomeScreenItemTour(tourItem: TourModel.TourItem) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Card(shape = RoundedCornerShape(8.dp)) {
            AsyncImage(
                model = tourItem.image.md,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.image_placeholder)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = tourItem.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,

                )
        )
        Text(
            text = tourItem.location,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Normal,

                )
        )
    }
}

@Composable
fun HomeScreenItemRoom(roomItem: RoomsModel.RoomItem) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Card(shape = RoundedCornerShape(8.dp)) {
            AsyncImage(
                model = roomItem.image.md,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.image_placeholder)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = roomItem.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,
            )
        )
        Text(
            text = roomItem.type, maxLines = 2, overflow = TextOverflow.Ellipsis, style = TextStyle(
                fontSize = 12.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Normal,
            )
        )
    }
}

@Composable
fun HomeScreenItemBlog(blogItem: BlogModel.BlogItem, itemClick: (blogId: Int) -> Unit) {
    Column(modifier = Modifier
        .padding(8.dp)
        .clickable {
            itemClick(blogItem.id)
        }) {
        Card(shape = RoundedCornerShape(8.dp)) {
            AsyncImage(
                model = blogItem.image.md,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.image_placeholder)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = blogItem.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Medium,

                )
        )
        Text(
            text = blogItem.subtitle,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Normal,

                )
        )
    }
}