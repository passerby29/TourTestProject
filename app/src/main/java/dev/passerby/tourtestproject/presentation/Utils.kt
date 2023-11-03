package dev.passerby.tourtestproject.presentation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.presentation.theme.appFontFamily
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

fun formatDate(date: String?): String {
    return if (date.isNullOrEmpty()) {
        date.toString()
    } else {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ITALIAN)
        formatter.format(parser.parse(date) as Date)
    }
}