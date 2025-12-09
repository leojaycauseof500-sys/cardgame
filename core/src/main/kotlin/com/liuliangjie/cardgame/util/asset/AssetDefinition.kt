package com.liuliangjie.cardgame.util.asset

import com.liuliangjie.cardgame.enums.AssetTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class AssetDefinition(
    val name: String,
    val type: AssetTypeEnum,
    val path: String,
    val properties: Map<String, String> = mapOf()
)