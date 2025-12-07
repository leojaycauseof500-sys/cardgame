package com.liuliangjie.cardgame.configuration.configuration

import com.liuliangjie.cardgame.enums.ConfigurationEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("UIConfiguration")
data class UIConfiguration(
    override val name: String = ConfigurationEnum.UI.name,
    val uiSkinUrl : String
) : GameConfiguration()