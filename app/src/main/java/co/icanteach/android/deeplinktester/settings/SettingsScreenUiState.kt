package co.icanteach.android.deeplinktester.settings

data class SettingsScreenUiState constructor(
    val isDarkThemeSelected: Boolean = false,
    private val showHistoryOrEmptyState: Boolean = false
) {

    fun onDarkThemeChanged(isDarkThemeSelected: Boolean): SettingsScreenUiState {
        return copy(isDarkThemeSelected = isDarkThemeSelected, showHistoryOrEmptyState = true)
    }

    fun shouldShowContent() = showHistoryOrEmptyState
}