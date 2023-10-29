package dev.passerby.tourtestproject.presentation

import dev.passerby.tourtestproject.R


sealed class BottomNavItem(var title: String, var icon: Int, var screenRoute: String) {

    data object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    data object Map : BottomNavItem("Map", R.drawable.ic_map, "map")
    data object Book : BottomNavItem("Book", R.drawable.ic_book, "book")
    data object Chat : BottomNavItem("Chat", R.drawable.ic_chat, "chat")
    data object More : BottomNavItem("More", R.drawable.ic_more, "more")
}