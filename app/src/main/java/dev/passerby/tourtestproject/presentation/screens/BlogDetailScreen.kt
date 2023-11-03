package dev.passerby.tourtestproject.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.passerby.tourtestproject.R
import dev.passerby.tourtestproject.presentation.formatDate
import dev.passerby.tourtestproject.presentation.theme.appFontFamily
import dev.passerby.tourtestproject.presentation.viewmodels.BlogDetailViewModel

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