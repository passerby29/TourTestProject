package dev.passerby.tourtestproject.presentation.navbar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.passerby.tourtestproject.presentation.theme.IconSelected
import dev.passerby.tourtestproject.presentation.theme.IconUnselected
import dev.passerby.tourtestproject.presentation.theme.LightSurface

@Composable
fun MyBottomNavigation(navController: NavController) {
    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Map,
        BottomNavItem.Book,
        BottomNavItem.Chat,
        BottomNavItem.More
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            val selected = currentRoute == screen.screenRoute
            NavigationBarItem(
                selected = selected,
                icon = {
                    Icon(
                        painterResource(id = screen.icon),
                        contentDescription = screen.title,
                        tint = if (selected) IconSelected else IconUnselected
                    )
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.surface),
                alwaysShowLabel = true,
                label = { Text(text = screen.title, fontSize = 9.sp) },
                onClick = {
                    navController.navigate(screen.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}