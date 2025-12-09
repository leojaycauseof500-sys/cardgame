package com.liuliangjie.cardgame.module

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.configuration.configuration.UIConfiguration
import com.liuliangjie.cardgame.enums.ConfigurationEnum
import com.liuliangjie.cardgame.enums.UIAssetEnum

class SceneModule : AbstractModule() {

    override fun configure() {
        install(UtilModule())

    }
}
