package com.liuliangjie.cardgame.util.asset

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.google.inject.Inject
import com.google.inject.Singleton
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.configuration.configuration.AssetConfiguration
import com.liuliangjie.cardgame.configuration.configuration.LanguageConfiguration
import com.liuliangjie.cardgame.enums.AssetTypeEnum.*
import com.liuliangjie.cardgame.enums.LanguageEnum
import com.liuliangjie.cardgame.enums.UIAssetEnum
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.internal.decodeByReader
import org.slf4j.LoggerFactory


/**
 * 使用kotlin重写的资源管理器
 * 支持从JSON配置文件中加载资产定义并自动实例化
 * @see com.badlogic.gdx.assets.AssetManager
 * @author liuliangjie
 */
@Singleton
class KAssetManage @Inject constructor(
    private val configurationManager: ConfigurationManager
) {
    private val logger = LoggerFactory.getLogger(KAssetManage::class.java)
    private val assets: MutableMap<String, Any> = mutableMapOf()
    private val json = Json { ignoreUnknownKeys = true }


    init {
        loadAssetsFromConfiguration()
    }

    /**
     * 从配置中加载资产
     */
    private fun loadAssetsFromConfiguration() {
        val languageConfig = configurationManager.getConfiguration(LanguageConfiguration::class) as LanguageConfiguration
        val currentLanguage = languageConfig.language
        val assetConfig = configurationManager.getConfiguration(AssetConfiguration::class) as AssetConfiguration

        assetConfig.assetDefinitions.forEach { definition ->
            try {
                when (definition.type) {
                    TEXTURE -> {
                        val texture = Texture(Gdx.files.internal(definition.path))
                        assets[definition.name] = texture
                    }
                    NINE_PATCH -> {
                        val left = definition.properties["left"]?.toIntOrNull() ?: 0
                        val right = definition.properties["right"]?.toIntOrNull() ?: 0
                        val top = definition.properties["top"]?.toIntOrNull() ?: 0
                        val bottom = definition.properties["bottom"]?.toIntOrNull() ?: 0
                        val texture = Texture(Gdx.files.internal(definition.path))
                        val ninePatch = NinePatch(texture, left, right, top, bottom)
                        assets[definition.name] = ninePatch
                    }
                    FONT -> {
                        val language = definition.properties["language"]?.let { LanguageEnum.valueOf(it) }
                            ?: throw IllegalArgumentException("Invalid language: ${definition.properties["language"]}")
                        if (currentLanguage != language) {
                            // 如果当前语言和指定的语言不一致，直接返回
                        }else {
                            val generator = FreeTypeFontGenerator(Gdx.files.internal(definition.path))
                            val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().apply {
                                size = definition.properties["size"]?.toIntOrNull() ?: 16
                                borderColor = definition.properties["borderColor"]?.let {
                                    Color.valueOf(it)
                                }
                                borderWidth = definition.properties["borderWidth"]?.toFloatOrNull() ?: 0f
                                color = definition.properties["color"]?.let {
                                    Color.valueOf(it)
                                } ?: Color.WHITE

                                // 如果指定了字符集，则使用指定的字符集
                                characters = definition.properties["characters"]
                                    ?: Gdx.files.internal("assets/characterset/${language.languageCode}.txt").readString()

                            }
                            // todo 。。。
                            val font = generator.generateFont(parameter)
                            generator.dispose()
                            assets[definition.name] = font
                        }


                    }

                    SOUND -> TODO()
                    MUSIC -> TODO()
                }
            } catch (e: Exception) {
                logger.error("Failed to load asset: ${definition.name}", e)
                throw e
            }
        }
    }

    /**
     * 获取资产
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getAsset(name: String): T {
        return assets[name] as T
    }

    /**
     * 释放所有资产
     */
    fun dispose() {
        assets.values.forEach { asset ->
            when (asset) {
                is Texture -> asset.dispose()
                is BitmapFont -> asset.dispose()
                is Sound -> asset.dispose()
                is Music -> asset.dispose()
                // NinePatch不需要dispose，它使用的Texture会在上面处理
            }
        }
        assets.clear()
    }
}

