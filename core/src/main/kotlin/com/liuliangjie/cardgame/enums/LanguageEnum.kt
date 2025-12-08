package com.liuliangjie.cardgame.enums

import java.util.Locale

enum class LanguageEnum(
    val languageCode: String,
    val locale: Locale
) {
    CHINESE("zhs", Locale.SIMPLIFIED_CHINESE),
    ENGLISH("eng", Locale.ENGLISH)
}