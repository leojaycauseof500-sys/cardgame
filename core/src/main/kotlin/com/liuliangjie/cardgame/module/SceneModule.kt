package com.liuliangjie.cardgame.module

import com.badlogic.gdx.Game
import com.google.inject.AbstractModule
import com.liuliangjie.cardgame.ui.screen.ScreenManager
import com.liuliangjie.cardgame.ui.screen.ScreenSwitcher

class SceneModule(val screenSwitcher: ScreenSwitcher) : AbstractModule() {

    override fun configure() {
        install(UtilModule())
        bind(ScreenSwitcher::class.java).toInstance(screenSwitcher)
    }
}