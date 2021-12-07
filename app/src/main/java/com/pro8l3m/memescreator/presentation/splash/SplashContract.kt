package com.pro8l3m.memescreator.presentation.splash

import com.pro8l3m.core.base.ViewEvent
import com.pro8l3m.core.base.ViewSideEffect
import com.pro8l3m.core.base.ViewState

class SplashContract {

    // What user does e.g. click the button, touch screen, etc..
    sealed class Event : ViewEvent {
        data class OnSplashFinished(val route: String) : Event()
    }

    // State of current screen (big model for whole screen)
    data class State(val isSplashFinished: Boolean = false, val isLoading: Boolean = false) : ViewState


    // Effect of Event. e.g. user clicked on navigation button (event) -> navigate to any screen (effect)
    sealed class Effect : ViewSideEffect {

        sealed class Navigation : Effect() {
            data class ToDashboardScreen(val dashboardScreenRoute: String) : Navigation()
        }
    }

}