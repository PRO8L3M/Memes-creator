package com.pro8l3m.memescreator.presentation.dashboard

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pro8l3m.core.base.BaseViewModel
import com.pro8l3m.domain.models.MemeModel
import com.pro8l3m.domain.usecases.GetMemesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getMemes: GetMemesUseCase
) : BaseViewModel<DashboardContract.Event, DashboardContract.State, DashboardContract.Effect>() {

    override fun setInitialState(): DashboardContract.State = DashboardContract.State(listOf())

    override fun handleEvents(event: DashboardContract.Event) {
        when (event) {
            is DashboardContract.Event.OnFetchMemesButtonClicked -> fetchMemes()
            is DashboardContract.Event.OnMemeClicked -> setEffect { DashboardContract.Effect.Navigation.NavigateToMemeOverview(event.route, event.meme) }
        }
    }

    fun onFetchButtonClicked() {
        setState { copy(isLoading = true) }
        setEvent(DashboardContract.Event.OnFetchMemesButtonClicked)
    }

    fun onMemeClicked(meme: MemeModel) {
        setEvent(DashboardContract.Event.OnMemeClicked("meme_overview", meme))
    }

    private fun fetchMemes() {
        getMemes(viewModelScope, Unit, ::onFetchMemesSuccess, ::onFetchMemesError)
    }

    private fun onFetchMemesSuccess(memes: List<MemeModel>) {
        setState { copy(memes = memes, isLoading = false) }
        setEffect { DashboardContract.Effect.FetchedMemes }
        Log.i(TAG, "Memes: $memes")
    }

    private fun onFetchMemesError(error: Throwable) {
        setState { copy(memes = listOf(), isLoading = false) }
        Log.e(TAG, error.stackTraceToString())
    }

    companion object {
        private const val TAG = "Memes"
    }
}