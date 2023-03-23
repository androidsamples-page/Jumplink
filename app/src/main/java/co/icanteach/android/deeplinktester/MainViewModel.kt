package co.icanteach.android.deeplinktester

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icanteach.android.deeplinktester.userpref.UserPreferences
import co.icanteach.android.deeplinktester.userpref.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _userThemePreference = MutableLiveData<UserPreferences>()
    val userThemePreference: LiveData<UserPreferences> = _userThemePreference

    init {
        observeUserPreferences()
    }

    private fun observeUserPreferences() {
        userPreferencesRepository.userPreferencesFlow.onEach { result ->
            _userThemePreference.value = result
        }.launchIn(viewModelScope)
    }
}