package com.liuliangjie.cardgame.configuration.loader

import com.liuliangjie.cardgame.configuration.configuration.BaseConfiguration
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

/**
 * 配置加载器SPI接口
 * 用于加载不同类型的配置
 */
interface ConfigurationLoader {
    /**
     * 加载配置
     * @return 加载的配置对象
     */
    fun load(): Collection<BaseConfiguration>

}