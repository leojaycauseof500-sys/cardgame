package com.liuliangjie.cardgame

import com.badlogic.gdx.Game
import com.google.inject.Guice
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.module.SceneModule
import com.liuliangjie.cardgame.ui.screen.MainMenuScreens

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : Game() {
    private lateinit var mainMenuScreens: MainMenuScreens

    override fun create() {
        // 创建依赖注入容器
        val injector = Guice.createInjector(SceneModule())

        val binds = injector.allBindings
        for (bind in binds) {
            println("${bind.key} -> ${bind.value}")
        }
        
        // 获取主菜单屏幕
        mainMenuScreens = injector.getInstance(MainMenuScreens::class.java)
        mainMenuScreens.setGame(this)
        setScreen(mainMenuScreens)
    }

}