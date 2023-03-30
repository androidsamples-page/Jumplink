package co.icanteach.jumplink

import java.util.UUID

object FakeDeepLinkItemFactory {

    fun createDeepLinkItems(): List<DeepLinkItem> {
        return mutableListOf(
            DeepLinkItem(
                id = UUID.randomUUID().toString(),
                deeplink = "xxx",
                createdDate = "29.03.2023"
            ),
            DeepLinkItem(
                id = UUID.randomUUID().toString(),
                deeplink = "xxx4",
                createdDate = "08.04.2023"
            ),
            DeepLinkItem(
                id = UUID.randomUUID().toString(),
                deeplink = "xxx3",
                createdDate = "07.05.2023"
            ),
            DeepLinkItem(
                id = UUID.randomUUID().toString(),
                deeplink = "xxx5",
                createdDate = "17.06.2023"
            ),
            DeepLinkItem(
                id = UUID.randomUUID().toString(),
                deeplink = "xxx5",
                createdDate = "12.07.2023"
            )
        )
    }

    fun createDeepLinkItem(): DeepLinkItem {
        return DeepLinkItem(
            id = UUID.randomUUID().toString(),
            deeplink = "xxx9",
            createdDate = "12.07.2023"
        )
    }
}

data class DeepLinkItem(
    val id: String,
    val deeplink: String,
    val createdDate: String
)
