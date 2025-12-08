package com.liuliangjie.cardgame.configuration.configuration

import com.liuliangjie.cardgame.enums.ConfigurationEnum
import com.liuliangjie.cardgame.enums.LanguageEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("LanguageConfiguration")
data class LanguageConfiguration(
    override val name: String = ConfigurationEnum.LANGUAGE.name,
    val language: LanguageEnum
) : BaseConfiguration()
