package com.liuliangjie.cardgame

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Singleton
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.module.SceneModule
import com.liuliangjie.cardgame.ui.screen.MainMenuScreens
import com.liuliangjie.cardgame.ui.screen.ScreenManager
import com.liuliangjie.cardgame.ui.screen.ScreenSwitcher

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
@Singleton
class Main : Game() {
    private lateinit var mainMenuScreens: MainMenuScreens
    // 注入器必须延迟初始化，等待gdx库加载完毕
    private lateinit var screenManager: ScreenManager
    override fun create() {
        screenManager = ScreenManager(this)
        screenManager.create()

    }
}