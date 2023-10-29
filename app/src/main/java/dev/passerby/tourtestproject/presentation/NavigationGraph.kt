package dev.passerby.tourtestproject.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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