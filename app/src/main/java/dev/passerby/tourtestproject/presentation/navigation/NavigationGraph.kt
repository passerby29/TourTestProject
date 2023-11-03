package dev.passerby.tourtestproject.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.presentation.screens.BlogDetailScreen
import dev.passerby.tourtestproject.presentation.screens.BookScreen
import dev.passerby.tourtestproject.presentation.screens.ChatScreen
import dev.passerby.tourtestproject.presentation.screens.HomeScreen
import dev.passerby.tourtestproject.presentation.screens.MapScreen
import dev.passerby.tourtestproject.presentation.screens.MoreScreen
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
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            HomeScreen(mainInfo,
                homeViewModel,
                itemClick = { navController.navigate(BottomNavItem.BlogDetail.passId(it)) })
        }
        composable(BottomNavItem.Map.screenRoute,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            MapScreen()
        }

        composable(BottomNavItem.Book.screenRoute,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            BookScreen()
        }
        composable(BottomNavItem.Chat.screenRoute,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            ChatScreen()
        }
        composable(BottomNavItem.More.screenRoute,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            MoreScreen()
        }
        composable(
            BottomNavItem.BlogDetail.screenRoute,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            arguments = listOf(navArgument("blogId") { type = NavType.IntType })
        ) { backStackEntry ->
            BlogDetailScreen(
                backStackEntry.arguments!!.getInt("blogId"), blogDetailViewModel, navController
            )
        }
    }
}