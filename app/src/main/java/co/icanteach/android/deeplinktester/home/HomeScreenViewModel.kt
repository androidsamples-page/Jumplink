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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        fetchDeepLinkHistory()
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
                onSaveItemToHistory(action.value)
            }
            HomeScreenActions.TestEnteredContent -> {
                val currentState = _uiState.value
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.NavigateDeepLinkContent(currentState.enteredContent))
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

    private fun fetchDeepLinkHistory() {
        fetchDeepLinkHistory
            .fetchDeepLinkHistory()
            .onEach { result ->
                val currentState = _uiState.value.copy(historyItems = result)
                onUpdatePageViewState(currentState)
            }.launchIn(viewModelScope)
    }
}

sealed class UiEvent {
    data class NavigateDeepLinkContent(val deepLinkContent: String) : UiEvent()
    data class ShowError(val message: String) : UiEvent()
}