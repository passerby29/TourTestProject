package dev.passerby.tourtestproject

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.passerby.tourtestproject.presentation.MyTopAppBar
import dev.passerby.tourtestproject.presentation.NavigationGraph
import dev.passerby.tourtestproject.presentation.navbar.BottomNavItem
import dev.passerby.tourtestproject.presentation.navbar.MyBottomNavigation
import dev.passerby.tourtestproject.presentation.viewmodels.BlogDetailViewModel
import dev.passerby.tourtestproject.presentation.viewmodels.HomeViewModel
import dev.passerby.tourtestproject.presentation.viewmodels.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun MainScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    homeViewModel: HomeViewModel,
    blogDetailViewModel: BlogDetailViewModel
) {
    var title by remember { mutableStateOf("") }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title = when (backStackEntry.destination.route) {
                BottomNavItem.Home.screenRoute -> BottomNavItem.Home.title
                BottomNavItem.BlogDetail.screenRoute -> BottomNavItem.BlogDetail.title
                BottomNavItem.Map.screenRoute -> BottomNavItem.Map.title
                BottomNavItem.Book.screenRoute -> BottomNavItem.Book.title
                BottomNavItem.Chat.screenRoute -> BottomNavItem.Chat.title
                BottomNavItem.More.screenRoute -> BottomNavItem.More.title
                else -> ""
            }
        }
    }

    Scaffold(topBar = { MyTopAppBar(screenTitle = title) },
        bottomBar = { MyBottomNavigation(navController = navController)  },
        content = {
            Box(modifier = Modifier.padding(it)) {
                NavigationGraph(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    blogDetailViewModel = blogDetailViewModel
                )
            }
        }
    )
}