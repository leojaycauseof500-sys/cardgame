package com.liuliangjie.cardgame.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.google.inject.Inject
import com.google.inject.Singleton
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.enums.UIAssetEnum

// todo 需要重构，后续提供加载的spi。

/**
 * 使用kotlin重写的资源管理器
 * @see com.badlogic.gdx.assets.AssetManager
 * @author liuliangjie
 */
@Singleton
class KAssetManage @Inject constructor(
    private val configurationManager: ConfigurationManager,
    private val generator : FreeTypeFontGenerator
) {
    private val assets : MutableMap<String, Any> = mutableMapOf()
    private val assetManager = AssetManager()
    init {
        assets[UIAssetEnum.DefaultButtonNinePatch.assetName] = NinePatch(Texture(Gdx.files.internal(UIAssetEnum.DefaultButtonNinePatch.url)), 10, 10, 10, 10)
        assets[UIAssetEnum.SourceHanMedium.assetName] = generator.generateFont(FreeTypeFontGenerator.FreeTypeFontParameter().apply {
            size = 20
            characters = FreeTypeFontGenerator.DEFAULT_CHARS + "开始新游戏存档读取退出"
        })
    }
    
    @Suppress("UNCHECKED_CAST")
    fun <T> getAsset(name : String) : T {
        return assets[name] as T
    }
}