package co.icanteach.android.deeplinktester.home

data class HomeScreenPageViewState constructor(
    val enteredContent: String = ""
) {

    fun onClearEnteredContent(): HomeScreenPageViewState {
        return copy(enteredContent = "")
    }
}