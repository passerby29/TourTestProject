package dev.passerby.tourtestproject.presentation.navigation

import dev.passerby.tourtestproject.R


sealed class BottomNavItem(var title: String, var icon: Int, var screenRoute: String) {
    data object Home : BottomNavItem("Главная", R.drawable.ic_home, "home")
    data object Map : BottomNavItem("Карта", R.drawable.ic_map, "map")
    data object Book : BottomNavItem("Бронь", R.drawable.ic_book, "book")
    data object Chat : BottomNavItem("Чат", R.drawable.ic_chat, "chat")
    data object More : BottomNavItem("Ещё", R.drawable.ic_more, "more")
    data object BlogDetail : BottomNavItem("Блог", R.drawable.ic_more, "blogDetail/{blogId}") {
        fun passId(blogId: Int) = "blogDetail/$blogId"
    }
}