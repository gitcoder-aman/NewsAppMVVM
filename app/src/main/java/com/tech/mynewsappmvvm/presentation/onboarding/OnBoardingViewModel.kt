package com.tech.mynewsappmvvm.presentation.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.mynewsappmvvm.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases : AppEntryUseCases
): ViewModel() {

    fun onEvent(event : OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry->{
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
            Log.i("@@@@", "OnBoardingViewModel: ${appEntryUseCases.saveAppEntry.invoke()}")
        }
    }
}