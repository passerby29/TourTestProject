package dev.passerby.tourtestproject.presentation.navbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.presentation.BlogDetailScreen
import dev.passerby.tourtestproject.presentation.BookScreen
import dev.passerby.tourtestproject.presentation.ChatScreen
import dev.passerby.tourtestproject.presentation.HomeScreen
import dev.passerby.tourtestproject.presentation.MapScreen
import dev.passerby.tourtestproject.presentation.MoreScreen
import dev.passerby.tourtestproject.presentation.viewmodels.HomeViewModel

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: HomeViewModel) {

    val blogContent = viewModel.blogContent.observeAsState().value ?: BlogModel(emptyList())

    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen(blogContent.blogList, itemClick = {
                navController.navigate("blogDetail/$it")
            })
        }
        composable(BottomNavItem.Map.screenRoute) {
            MapScreen()
        }

        composable(BottomNavItem.Book.screenRoute) {
            BookScreen()
        }
        composable(BottomNavItem.Chat.screenRoute) {
            ChatScreen()
        }
        composable(BottomNavItem.More.screenRoute) {
            MoreScreen()
        }
        composable(
            "blogDetail/{blogId}",
            arguments = listOf(navArgument("blogId") { type = NavType.IntType })
        ) {
            BlogDetailScreen()
        }
    }
}