package com.liuliangjie.cardgame.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.google.inject.Inject
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.enums.UIAssetEnum
import jakarta.inject.Singleton

@Singleton
class KAssetManage @Inject constructor(
    private val configurationManager: ConfigurationManager
) {
    private val assets : MutableMap<String, Any> = mutableMapOf()

    init {
        assets[UIAssetEnum.DefaultButtonNinePatch.assetName] = NinePatch(Texture(Gdx.files.internal(UIAssetEnum.DefaultButtonNinePatch.url)), 10, 10, 10, 10)
    }
    
    @Suppress("UNCHECKED_CAST")
    fun <T> getAsset(name : String) : T {
        return assets[name] as T
    }
}