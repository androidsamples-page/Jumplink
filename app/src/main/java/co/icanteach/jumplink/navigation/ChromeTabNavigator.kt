package co.icanteach.jumplink.navigation

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object ChromeTabNavigator {

    fun openAppSourceCode(context: Context) {

        val activity = (context as? Activity)
        val appLink = "https://github.com/androidsamples-page/Jumplink"

        try {
            val chromePackageName = "com.android.chrome"
            (context as? Activity)
            val builder = CustomTabsIntent.Builder()
            builder.setShowTitle(true)
            builder.setInstantAppsEnabled(true)
            val customBuilder = builder.build()
            customBuilder.intent.setPackage(chromePackageName)
            customBuilder.launchUrl(context, Uri.parse(appLink))

        } catch (_: ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(appLink))
            activity?.startActivity(intent)
        }
    }
}