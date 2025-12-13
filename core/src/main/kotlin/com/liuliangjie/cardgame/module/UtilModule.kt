package com.liuliangjie.cardgame.module

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import com.google.inject.name.Names
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.configuration.loader.ConfigurationLoader
import com.liuliangjie.cardgame.configuration.loader.impl.AssetConfigurationLoader
import com.liuliangjie.cardgame.configuration.loader.impl.GameConfigurationLoader
import com.liuliangjie.cardgame.i18n.I18NService
import com.liuliangjie.cardgame.i18n.impl.UII18NServiceImpl

class UtilModule : AbstractModule() {

    override fun configure() {
        val loaderMultiBinder = Multibinder.newSetBinder(binder(), ConfigurationLoader::class.java)
        loaderMultiBinder.addBinding().to(GameConfigurationLoader::class.java)
        loaderMultiBinder.addBinding().to(AssetConfigurationLoader::class.java)
        this.bind(ConfigurationManager::class.java).asEagerSingleton()
        bind(I18NService::class.java)
            .annotatedWith(Names.named("UI"))
            .to(UII18NServiceImpl::class.java)
            .asEagerSingleton()
    }
}