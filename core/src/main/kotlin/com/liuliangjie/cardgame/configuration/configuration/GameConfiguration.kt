package com.liuliangjie.cardgame.configuration.configuration

import kotlinx.serialization.Serializable

@Serializable
sealed class GameConfiguration{
    abstract val name : String
}
