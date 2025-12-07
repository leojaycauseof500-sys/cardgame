package com.liuliangjie.cardgame.module

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.configuration.configuration.UIConfiguration
import com.liuliangjie.cardgame.enums.ConfigurationEnum

class SceneMudule : AbstractModule() {
    private val configurationManager = ConfigurationManager()

    override fun configure() {
        this.bind(ConfigurationManager::class.java) to configurationManager
        this.bind(AssetManager::class.java) to AssetManager()

    }

    @Provides
    fun provideSkin(): Skin {
        val uiConfiguration = configurationManager.getConfiguration(ConfigurationEnum.UI.name) as UIConfiguration?
        return Skin(Gdx.files.internal(uiConfiguration?.uiSkinUrl))
    }
}
