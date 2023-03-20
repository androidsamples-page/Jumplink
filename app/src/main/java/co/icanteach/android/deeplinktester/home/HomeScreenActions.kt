package co.icanteach.android.deeplinktester.home

sealed class HomeScreenActions {
    data class EnteredContent(val value: String) : HomeScreenActions()
    object TestEnteredContent : HomeScreenActions()
    object ClearEnteredContent : HomeScreenActions()
}