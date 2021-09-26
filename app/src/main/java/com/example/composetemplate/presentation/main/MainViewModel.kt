package com.example.composetemplate.presentation.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.datastore.IDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: IDataStore,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _themeMode = MutableStateFlow(LIGHT_MODE)
    val themeMode: StateFlow<Int> = _themeMode

    init {
        viewModelScope.launch {
            dataStore.getThemeMode().collect { themeMode ->
                Log.w("themeMode", "Theme: $themeMode")
                _themeMode.value = themeMode
            }
        }
    }

    fun setThemeMode(mode: Int) {
        viewModelScope.launch {
            dataStore.saveThemeMode(mode)
        }
    }

    companion object {
        const val LIGHT_MODE = 0
        const val DARK_MODE = 1
    }
}