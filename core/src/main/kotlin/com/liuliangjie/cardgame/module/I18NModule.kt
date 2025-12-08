package com.liuliangjie.cardgame.module

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.liuliangjie.cardgame.i18n.I18NService
import com.liuliangjie.cardgame.i18n.impl.UII18NServiceImpl

class I18NModule : AbstractModule() {
    override fun configure() {
        install(UtilModule())
        bind(I18NService::class.java)
            .annotatedWith(Names.named("UI"))
            .to(UII18NServiceImpl::class.java)
            .asEagerSingleton()
    }
}