package co.icanteach.android.deeplinktester.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icanteach.android.deeplinktester.deeplinkhistory.domain.FetchDeepLinkHistoryUseCase
import co.icanteach.android.deeplinktester.deeplinkhistory.domain.SaveDeepLinkToHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val fetchDeepLinkHistory: FetchDeepLinkHistoryUseCase,
    private val saveItemToHistory: SaveDeepLinkToHistoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenPageViewState())
    val uiState: StateFlow<HomeScreenPageViewState> = _uiState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        observeDeepLinkHistory()
    }

    fun onAction(action: HomeScreenActions) {
        when (action) {
            HomeScreenActions.ClearEnteredContent -> {
                val currentState = _uiState.value
                onUpdatePageViewState(currentState.onClearEnteredContent())
            }
            is HomeScreenActions.EnteredContent -> {
                val currentState = _uiState.value
                onUpdatePageViewState(currentState.onEnteredContent(newValue = action.value))
            }
            HomeScreenActions.TestEnteredContent -> {
                val currentState = _uiState.value

                if (currentState.isEnteredContentValid()) {
                    viewModelScope.launch {
                        _eventFlow.emit(UiEvent.NavigateDeepLinkContent(currentState.enteredContent))
                    }
                    onSaveItemToHistory(currentState.enteredContent)
                }
            }
            is HomeScreenActions.TestHistoryItemContent -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.NavigateDeepLinkContent(action.item.deeplink))
                }
            }
        }
    }

    private fun onSaveItemToHistory(value: String) {
        viewModelScope.launch {
            saveItemToHistory.saveItem(value)
        }
    }

    private fun onUpdatePageViewState(pageViewState: HomeScreenPageViewState) {
        _uiState.value = pageViewState
    }

    private fun observeDeepLinkHistory() {
        viewModelScope.launch {
            fetchDeepLinkHistory
                .fetchDeepLinkHistory()
                .collect { result ->
                    val currentState = _uiState.value.onUpdateHistoryItem(result)
                    onUpdatePageViewState(currentState)
                }
        }
    }
}

sealed class UiEvent {
    data class NavigateDeepLinkContent(val deepLinkContent: String) : UiEvent()
}