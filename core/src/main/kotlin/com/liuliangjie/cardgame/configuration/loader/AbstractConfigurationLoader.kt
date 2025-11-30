package com.liuliangjie.cardgame.configuration.loader

import com.google.inject.Singleton
import kotlinx.serialization.json.Json

@Singleton
abstract class AbstractConfigurationLoader : ConfigurationLoader {
    protected val json = Json { ignoreUnknownKeys = true }
    protected abstract val configuration : Any

    override fun load(): Any = configuration
}
