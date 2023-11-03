package dev.passerby.tourtestproject.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.presentation.navbar.BottomNavItem
import dev.passerby.tourtestproject.presentation.viewmodels.BlogDetailViewModel
import dev.passerby.tourtestproject.presentation.viewmodels.HomeViewModel

@Composable
@ExperimentalAnimationApi
fun NavigationGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    blogDetailViewModel: BlogDetailViewModel
) {
    val mainInfo = homeViewModel.mainInfo.observeAsState().value ?: MainModel(
        MainModel.MainInfo(
            emptyList(), emptyList()
        ), "", false, ""
    )
    NavHost(
        navController = navController, startDestination = BottomNavItem.Home.screenRoute
    ) {
        composable(route = BottomNavItem.Home.screenRoute,
            enterTransition = { enterAnim() },
            exitTransition = { exitAnim() }) {
            HomeScreen(mainInfo,
                homeViewModel,
                itemClick = { navController.navigate(BottomNavItem.BlogDetail.passId(it)) })
        }
        composable(BottomNavItem.Map.screenRoute,
            enterTransition = { enterAnim() },
            exitTransition = { exitAnim() }) {
            MapScreen()
        }

        composable(BottomNavItem.Book.screenRoute,
            enterTransition = { enterAnim() },
            exitTransition = { exitAnim() }) {
            BookScreen()
        }
        composable(BottomNavItem.Chat.screenRoute,
            enterTransition = { enterAnim() },
            exitTransition = { exitAnim() }) {
            ChatScreen()
        }
        composable(BottomNavItem.More.screenRoute,
            enterTransition = { enterAnim() },
            exitTransition = { exitAnim() }) {
            MoreScreen()
        }
        composable(
            BottomNavItem.BlogDetail.screenRoute,
            enterTransition = { enterAnim() },
            exitTransition = { exitAnim() },
            arguments = listOf(navArgument("blogId") { type = NavType.IntType })
        ) { backStackEntry ->
            BlogDetailScreen(
                backStackEntry.arguments!!.getInt("blogId"), blogDetailViewModel, navController
            )
        }
    }
}

fun enterAnim() = slideInHorizontally(animationSpec = tween(1000), initialOffsetX = { 1000 })
fun exitAnim() = slideOutHorizontally(animationSpec = tween(1000), targetOffsetX = { -1000 })