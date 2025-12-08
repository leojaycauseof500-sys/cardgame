package com.liuliangjie.cardgame.configuration.configuration

import kotlinx.serialization.Serializable

@Serializable
sealed class BaseConfiguration{
    abstract val name : String
}
