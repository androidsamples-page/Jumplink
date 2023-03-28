package co.icanteach.jumplink.settings

interface SettingsScreenActions {
    data class OnDarkThemeChanged(val isDarkThemeEnabled: Boolean) : SettingsScreenActions
}