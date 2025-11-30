package com.liuliangjie.cardgame.configuration

import com.google.inject.Inject
import com.google.inject.Singleton
import com.liuliangjie.cardgame.configuration.loader.ConfigurationLoader
import ktx.collections.toGdxMap
import org.slf4j.LoggerFactory

@Singleton
class ConfigurationManager @Inject constructor(
    private val loaders : Set<@JvmSuppressWildcards ConfigurationLoader>
) {
    private val logger = LoggerFactory.getLogger(ConfigurationLoader::class.java)
    private val configurations = mutableMapOf<String, Any>()
    init {

    }

    fun reloadAll() {
        configurations.clear()

        configurations.putAll(loaders.associate {it.name to it.load() })

    }
}
