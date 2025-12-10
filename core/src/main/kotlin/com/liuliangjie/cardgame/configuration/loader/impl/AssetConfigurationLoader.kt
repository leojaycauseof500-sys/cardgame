package com.liuliangjie.cardgame.configuration.loader.impl

/**
 * 资产配置加载器
 * 用于从JSON文件加载资产配置定义，后续由资产管理器在加载
 * @see com.liuliangjie.cardgame.util.asset.AssetDefinition
 */
class AssetConfigurationLoader : AbstractLoader () {
    override val fileName: String = "config/asset.json"
}