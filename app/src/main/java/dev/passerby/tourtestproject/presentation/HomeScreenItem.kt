package dev.passerby.tourtestproject.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.passerby.tourtestproject.R
import dev.passerby.tourtestproject.domain.models.BlogItem

@Composable
fun HomeScreenItem(blogItem: BlogItem, itemClick: (blogId: Int) ->Unit) {
    Column(modifier = Modifier
        .padding(8.dp)
        .clickable {
            itemClick(blogItem.id)
        }) {
        Card(shape = RoundedCornerShape(8.dp)) {
            AsyncImage(
                model = blogItem.image.md,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_launcher_background)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = blogItem.title,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = blogItem.subtitle, maxLines = 2, overflow = TextOverflow.Ellipsis,
        )
    }
}