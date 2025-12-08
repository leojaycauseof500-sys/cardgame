package com.liuliangjie.cardgame.module

import com.badlogic.gdx.assets.AssetManager
import com.google.inject.AbstractModule
import com.liuliangjie.cardgame.configuration.ConfigurationManager

class UtilModule : AbstractModule() {

    override fun configure() {
        this.bind(ConfigurationManager::class.java).asEagerSingleton()
    }
}