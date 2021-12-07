package com.pro8l3m.memescreator.presentation.splash

import androidx.lifecycle.viewModelScope
import com.pro8l3m.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() :
    BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {

    init {
        viewModelScope.launch { startSplashScreen() }
    }

    private suspend fun startSplashScreen() {
        delay(2000L)
        setEvent(SplashContract.Event.OnSplashFinished("dashboard"))
        setState { copy(isSplashFinished = true) }
    }

    override fun setInitialState(): SplashContract.State = SplashContract.State()

    override fun handleEvents(event: SplashContract.Event) {
        when(event) {
            is SplashContract.Event.OnSplashFinished -> setEffect { SplashContract.Effect.Navigation.ToDashboardScreen(event.route) }
        }
    }
}