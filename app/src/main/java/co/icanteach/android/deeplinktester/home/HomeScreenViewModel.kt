package co.icanteach.android.deeplinktester.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenPageViewState())
    val uiState: StateFlow<HomeScreenPageViewState> = _uiState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onAction(action: HomeScreenActions) {
        when (action) {
            HomeScreenActions.ClearEnteredContent -> {
                val currentState = _uiState.value
                onUpdatePageViewState(currentState.onClearEnteredContent())
            }
            is HomeScreenActions.EnteredContent -> {
                val currentState = _uiState.value
                onUpdatePageViewState(currentState.copy(enteredContent = action.value))
            }
            HomeScreenActions.TestEnteredContent -> {
                val currentState = _uiState.value
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.NavigateDeepLinkContent(currentState.enteredContent))
                }
            }
        }
    }

    private fun onUpdatePageViewState(pageViewState: HomeScreenPageViewState) {
        _uiState.value = pageViewState
    }
}

sealed class UiEvent {
    data class NavigateDeepLinkContent(val deepLinkContent: String) : UiEvent()
    data class ShowError(val message: String) : UiEvent()
}