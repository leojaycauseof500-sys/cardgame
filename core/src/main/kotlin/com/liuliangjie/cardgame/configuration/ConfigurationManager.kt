package com.liuliangjie.cardgame.configuration

import com.google.inject.Inject
import com.google.inject.Singleton
import com.liuliangjie.cardgame.configuration.configuration.BaseConfiguration
import com.liuliangjie.cardgame.configuration.loader.ConfigurationLoader
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

@Singleton
class ConfigurationManager @Inject constructor(
    private val loaders : Set<ConfigurationLoader>
) {
    private val logger = LoggerFactory.getLogger(ConfigurationManager::class.java)
    private lateinit var configurations: Map<KClass<out BaseConfiguration>, BaseConfiguration>


    init {
        // 加载所有配置
        reloadAll()
    }

    fun reloadAll() {
        configurations = loaders.asSequence().flatMap { loader ->
            loader.load()
        }.associateBy { it::class }
    }

    fun getConfiguration(clazz : KClass<out BaseConfiguration>) = configurations[clazz]

}