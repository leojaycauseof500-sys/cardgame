package com.liuliangjie.cardgame.configuration.loader.impl

/**
 * 游戏配置加载器
 * 用于从JSON文件加载游戏配置（UI配置、语言配置等）
 */
class GameConfigurationLoader : AbstractLoader() {
    override val fileName: String = "assets/config/setting.json"
}