package com.liuliangjie.cardgame.configuration

import com.badlogic.gdx.Gdx
import com.google.inject.Singleton
import com.liuliangjie.cardgame.configuration.configuration.BaseConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

@Singleton
class ConfigurationManager {
    private val logger = LoggerFactory.getLogger(ConfigurationManager::class.java)
    private lateinit var configurations : Map<String, BaseConfiguration>
    private val json = Json { 
        ignoreUnknownKeys = true
    }

    // JSON配置文件所在目录
    private val configDir = "assets/config/"

    init {
        // 加载所有配置
        reloadAll()
    }

    fun reloadAll() {
        val fileName = "${configDir}setting.json"

        try {
            val configsJson = Gdx.files.internal(fileName).readString()

            val wrapper = json.decodeFromString<ConfigWrapper>(configsJson)

            configurations = wrapper.configs.associateBy { it.name }
        } catch (e: Exception) {
            logger.error("加载配置文件失败: $fileName", e)
            configurations = emptyMap()
        }
    }

    fun getConfiguration(name : String) = configurations[name]

    @Serializable
    private data class ConfigWrapper(
        val configs: List<BaseConfiguration>
    )
}