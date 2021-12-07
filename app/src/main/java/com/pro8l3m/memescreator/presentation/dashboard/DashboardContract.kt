package com.pro8l3m.memescreator.presentation.dashboard

import com.pro8l3m.core.base.ViewEvent
import com.pro8l3m.core.base.ViewSideEffect
import com.pro8l3m.core.base.ViewState
import com.pro8l3m.domain.models.MemeModel

class DashboardContract {

    // What user does e.g. click the button, touch screen, etc..
    sealed class Event : ViewEvent {
        object OnFetchMemesButtonClicked : Event()
        data class OnMemeClicked(val route: String, val meme: MemeModel) : Event()
    }

    // State of current screen (big model for whole screen)
    data class State(val memes: List<MemeModel>, val isLoading: Boolean = false) : ViewState

    // Effect of Event. e.g. user clicked on navigation button (event) -> navigate to any screen (effect)
    sealed class Effect : ViewSideEffect {
        object FetchedMemes : Effect()

        sealed class Navigation : Effect() {
            data class NavigateToMemeOverview(val route: String, val meme: MemeModel) : Navigation()
        }
    }

}