package co.icanteach.android.deeplinktester.settings

sealed class SettingsScreenActions {
    data class OnDarkThemeChanged(val isDarkThemeEnabled: Boolean) : SettingsScreenActions()
}