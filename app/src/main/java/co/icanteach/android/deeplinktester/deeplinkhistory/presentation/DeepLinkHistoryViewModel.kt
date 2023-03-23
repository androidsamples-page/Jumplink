package co.icanteach.android.deeplinktester.deeplinkhistory.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icanteach.android.deeplinktester.DeepLinkItem
import co.icanteach.android.deeplinktester.deeplinkhistory.domain.DeleteDeepLinkItemUseCase
import co.icanteach.android.deeplinktester.deeplinkhistory.domain.FetchDeepLinkHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DeepLinkHistoryViewModel @Inject constructor(
    private val fetchDeepLinkHistory: FetchDeepLinkHistoryUseCase,
    private val deleteDeepLinkItemUseCase: DeleteDeepLinkItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DeepLinkHistoryPageViewState())
    val uiState: StateFlow<DeepLinkHistoryPageViewState> = _uiState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        observeDeepLinkHistory()
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

    private fun onUpdatePageViewState(pageViewState: DeepLinkHistoryPageViewState) {
        _uiState.value = pageViewState
    }

    fun onEvent(action: DeepLinkHistoryActions) {
        when (action) {
            DeepLinkHistoryActions.ClearAllHistory -> {
                onClearAllHistory()
            }
            is DeepLinkHistoryActions.ClearHistoryItemContent -> {
                onClearDeepLinkItem(action.item)
            }
            is DeepLinkHistoryActions.TestHistoryItemContent -> {
                viewModelScope.launch {
                    _eventFlow.emit(
                        UiEvent.NavigateDeepLinkContent(
                            action.item.deeplink
                        )
                    )
                }
            }
        }
    }

    private fun onClearDeepLinkItem(item: DeepLinkItem) {
        viewModelScope.launch {
            deleteDeepLinkItemUseCase.deleteItem(item)
        }
    }

    private fun onClearAllHistory() {
        viewModelScope.launch {
            deleteDeepLinkItemUseCase.deleteAll()
        }
    }
}

sealed class UiEvent {
    data class NavigateDeepLinkContent(val deepLinkContent: String) : UiEvent()
}