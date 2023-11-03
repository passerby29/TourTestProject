package dev.passerby.tourtestproject.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import dev.passerby.tourtestproject.presentation.theme.TourTestProjectTheme
import dev.passerby.tourtestproject.presentation.viewmodels.BlogDetailViewModel
import dev.passerby.tourtestproject.presentation.viewmodels.HomeViewModel

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    private val homeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val blogDetailViewModel by lazy {
        ViewModelProvider(this)[BlogDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TourTestProjectTheme {
                val navController = rememberNavController()
                MainScreen(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    blogDetailViewModel = blogDetailViewModel
                )
            }
        }
    }
}