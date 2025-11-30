package com.liuliangjie.cardgame.configuration.loader


interface ConfigurationLoader {
    /** 加载器名称，用于标识和调试 */
    val name: String

    /** 加载配置并返回配置模型 */
    fun load(): Any

}
