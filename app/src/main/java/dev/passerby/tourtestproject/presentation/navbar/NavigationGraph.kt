package dev.passerby.tourtestproject.presentation.navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.passerby.tourtestproject.presentation.BookScreen
import dev.passerby.tourtestproject.presentation.ChatScreen
import dev.passerby.tourtestproject.presentation.HomeScreen
import dev.passerby.tourtestproject.presentation.MapScreen
import dev.passerby.tourtestproject.presentation.MoreScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute){
        composable(BottomNavItem.Home.screenRoute){
            HomeScreen()
        }
        composable(BottomNavItem.Map.screenRoute){
            MapScreen()
        }

        composable(BottomNavItem.Book.screenRoute){
            BookScreen()
        }
        composable(BottomNavItem.Chat.screenRoute){
            ChatScreen()
        }
        composable(BottomNavItem.More.screenRoute){
            MoreScreen()
        }
    }
}