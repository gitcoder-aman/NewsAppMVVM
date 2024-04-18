package com.tech.mynewsappmvvm.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.mynewsappmvvm.domain.usecases.AppEntryUseCases
import com.tech.mynewsappmvvm.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases : AppEntryUseCases
) : ViewModel() {

     var splashCondition = mutableStateOf(true)
         private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
              private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen->

            Log.i("@@@@", "MainViewModel: $shouldStartFromHomeScreen")
            if(shouldStartFromHomeScreen){
                startDestination = Route.NewsNavigation.route
            }else{
                startDestination = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition.value = false
            Log.i("@@@@", "MainViewModel: $splashCondition")
        }.launchIn(viewModelScope)
    }
}