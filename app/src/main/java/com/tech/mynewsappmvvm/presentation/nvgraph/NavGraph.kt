package com.tech.mynewsappmvvm.presentation.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.tech.mynewsappmvvm.presentation.home.HomeScreen
import com.tech.mynewsappmvvm.presentation.home.HomeViewModel
import com.tech.mynewsappmvvm.presentation.onboarding.OnBoardingScreen
import com.tech.mynewsappmvvm.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination : String
) {
    val navController = rememberNavController()

    NavHost(navController = navController,startDestination= startDestination){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ){
            composable(
                route = Route.NewsNavigationScreen.route
            ){
                val viewModel : HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles) {

                }
            }
        }
    }
}