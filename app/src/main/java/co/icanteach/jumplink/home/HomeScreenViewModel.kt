package co.icanteach.jumplink.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icanteach.jumplink.deeplinkhistory.domain.SaveDeepLinkToHistoryUseCase
import co.icanteach.jumplink.home.domain.FetchLastUsedDeepLinksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val fetchLastUsedDeepLinksUseCase: FetchLastUsedDeepLinksUseCase,
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
                onUiEventUpdate(UiEvent.NavigateDeepLinkContent(action.item.deeplink))
            }
            HomeScreenActions.PasteContentClipboard -> {
                onUiEventUpdate(UiEvent.PasteDeepLinkContent)
            }
        }
    }

    private fun onSaveItemToHistory(value: String) {
        viewModelScope.launch {
            try {
                saveItemToHistory.saveItem(value)
            } catch (_: Exception) {
            }
        }
    }

    private fun onUpdatePageViewState(pageViewState: HomeScreenPageViewState) {
        _uiState.value = pageViewState
    }

    private fun onUiEventUpdate(uiEvent: UiEvent) {
        viewModelScope.launch {
            _eventFlow.emit(uiEvent)
        }
    }

    private fun observeDeepLinkHistory() {
        viewModelScope.launch {
            fetchLastUsedDeepLinksUseCase
                .fetchDeepLinkHistory()
                .collect { result ->
                    val currentState = _uiState.value.onUpdateHistoryItem(result)
                    onUpdatePageViewState(currentState)
                }
        }
    }
}

sealed interface UiEvent {
    data class NavigateDeepLinkContent(val deepLinkContent: String) : UiEvent
    object PasteDeepLinkContent : UiEvent
}