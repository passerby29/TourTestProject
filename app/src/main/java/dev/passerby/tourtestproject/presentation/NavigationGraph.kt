package dev.passerby.tourtestproject.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.MainInfo
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.presentation.navbar.BottomNavItem
import dev.passerby.tourtestproject.presentation.viewmodels.BlogDetailViewModel
import dev.passerby.tourtestproject.presentation.viewmodels.HomeViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    blogDetailViewModel: BlogDetailViewModel
) {

    val mainInfo = homeViewModel.mainInfo.observeAsState().value ?: MainModel(
        MainInfo(
            emptyList(),
            emptyList()
        ),
        "",
        false,
        ""
    )
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen(mainInfo, homeViewModel, itemClick = {
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
        ) { backStackEntry ->
            BlogDetailScreen(
                backStackEntry.arguments?.getInt("blogId") ?: 0,
                blogDetailViewModel,
                navController
            )
        }
    }
}