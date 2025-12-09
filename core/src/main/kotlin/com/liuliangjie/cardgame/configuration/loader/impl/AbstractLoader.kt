package com.liuliangjie.cardgame.configuration.loader.impl

import com.badlogic.gdx.Gdx
import com.liuliangjie.cardgame.configuration.configuration.BaseConfiguration
import com.liuliangjie.cardgame.configuration.loader.ConfigurationLoader
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class AbstractLoader : ConfigurationLoader {
    protected val json = Json { ignoreUnknownKeys = true }
    protected val logger: Logger = LoggerFactory.getLogger(this::class.java)
    protected abstract val fileName: String

    override fun load(): Collection<BaseConfiguration> {
        return try {
            val configsJson = Gdx.files.internal(fileName).readString()
            val wrapper = json.decodeFromString<ConfigWrapper>(configsJson)
            wrapper.configs
        } catch (e: Exception) {
            logger.error("加载配置文件失败 loader: ${this::class.simpleName}", e)
            throw  e
        }
    }

    @Serializable
    private data class ConfigWrapper(
        val configs: List<BaseConfiguration>
    )
}