package com.liuliangjie.cardgame.configuration.configuration

import com.liuliangjie.cardgame.enums.ConfigurationEnum
import com.liuliangjie.cardgame.util.asset.AssetDefinition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AssetConfiguration")
data class AssetConfiguration(
    override val name: String = ConfigurationEnum.ASSET.name,
    val assetDefinitions: List<AssetDefinition>
) : BaseConfiguration()



