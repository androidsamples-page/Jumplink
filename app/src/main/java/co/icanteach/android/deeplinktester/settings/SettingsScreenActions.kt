package co.icanteach.android.deeplinktester.settings

interface SettingsScreenActions {
    data class OnDarkThemeChanged(val isDarkThemeEnabled: Boolean) : SettingsScreenActions
}