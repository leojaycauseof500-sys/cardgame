package com.liuliangjie.cardgame.ui.screen

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.google.inject.Guice
import com.google.inject.Injector
import com.liuliangjie.cardgame.module.SceneModule

class ScreenManager(val game: Game) : ScreenSwitcher {
    // 注入器必须延迟初始化，等待gdx库加载完毕
    private lateinit var injector: Injector

    fun create(){
        this.injector = Guice.createInjector(SceneModule(this))
        switchTo(MainMenuScreens::class.java)
    }
    override fun switchTo(screen: Class<out Screen>) {
        val newScreen = injector.getInstance(screen)

        game.setScreen(newScreen)
    }
}