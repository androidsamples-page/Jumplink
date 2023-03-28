package co.icanteach.jumplink

import java.util.UUID

object FakeDeepLinkItemFactory {

    fun createDeepLinkItems(): List<DeepLinkItem> {
        return mutableListOf(
            DeepLinkItem(id = UUID.randomUUID().toString(), deeplink = "xxx"),
            DeepLinkItem(id = UUID.randomUUID().toString(), deeplink = "xxx4"),
            DeepLinkItem(id = UUID.randomUUID().toString(), deeplink = "xxx3"),
            DeepLinkItem(id = UUID.randomUUID().toString(), deeplink = "xxx5"),
            DeepLinkItem(id = UUID.randomUUID().toString(), deeplink = "xxx5")
        )
    }

    fun createDeepLinkItem(): DeepLinkItem {
        return DeepLinkItem(id = UUID.randomUUID().toString(), deeplink = "xxx")
    }
}

data class DeepLinkItem(
    val id: String, val deeplink: String
)
