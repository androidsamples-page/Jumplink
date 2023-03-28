package co.icanteach.jumplink.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icanteach.jumplink.userpref.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsScreenUiState())
    val uiState: StateFlow<SettingsScreenUiState> = _uiState

    init {
        initAccountScreen()
    }

    fun onEvent(event: SettingsScreenActions) {
        when (event) {
            is SettingsScreenActions.OnDarkThemeChanged -> {
                onDarkThemeChanged(event.isDarkThemeEnabled)
            }
        }
    }

    private fun initAccountScreen() {
        userPreferencesRepository
            .userPreferencesFlow.onEach { userPreferences ->
                onUpdateAccountScreenState(
                    SettingsScreenUiState(isDarkThemeSelected = userPreferences.isDarkThemeSelected)
                )
            }.launchIn(viewModelScope)
    }

    private fun onDarkThemeChanged(isDarkThemeSelected: Boolean) {
        val currentState = uiState.value
        onUpdateAccountScreenState(currentState.onDarkThemeChanged(isDarkThemeSelected = isDarkThemeSelected))
        viewModelScope.launch {
            userPreferencesRepository.updateOnDarkThemeSelection(isDarkThemeSelected)
        }
    }

    private fun onUpdateAccountScreenState(screenState: SettingsScreenUiState) {
        _uiState.value = screenState
    }
}