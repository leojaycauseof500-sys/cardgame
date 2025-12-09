package com.liuliangjie.cardgame.enums

import kotlinx.serialization.Serializable

@Serializable
enum class AssetTypeEnum {
    TEXTURE,
    NINE_PATCH,
    FONT,
    SOUND,
    MUSIC
}