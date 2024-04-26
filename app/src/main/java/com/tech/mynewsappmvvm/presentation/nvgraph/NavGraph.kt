package com.tech.mynewsappmvvm.presentation.nvgraph

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.tech.mynewsappmvvm.domain.model.Article
import com.tech.mynewsappmvvm.domain.model.Source
import com.tech.mynewsappmvvm.domain.model.toEncodedString
import com.tech.mynewsappmvvm.presentation.bookmark.BookmarkScreen
import com.tech.mynewsappmvvm.presentation.bookmark.BookmarkViewModel
import com.tech.mynewsappmvvm.presentation.details.DetailScreen
import com.tech.mynewsappmvvm.presentation.home.HomeScreen
import com.tech.mynewsappmvvm.presentation.home.HomeViewModel
import com.tech.mynewsappmvvm.presentation.onboarding.OnBoardingScreen
import com.tech.mynewsappmvvm.presentation.onboarding.OnBoardingViewModel
import com.tech.mynewsappmvvm.presentation.search.SearchScreen
import com.tech.mynewsappmvvm.presentation.search.SearchViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ) {
            composable(
                route = Route.NewsNavigationScreen.route
            ) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(state = viewModel.state.value) {

                }
            }
        }
//        composable(
//            route = "${Route.DetailsScreen.route}/{data}",
//            arguments = listOf(navArgument("data") {
//                type = NavType.StringType
//            })
//        ) { backStackEntry ->
//            val data = backStackEntry.arguments?.getString("data")
//            val article = data?.let { Article.fromEncodedString(it) }
//            DetailScreen(article = article!!, event = {}) {
//                navController.navigateUp()
//            }
//        }
        composable(route = Route.SearchScreen.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(state = viewModel.state.value, event = viewModel::onEvent) {
                navController.navigate(it)
            }
        }
    }
}