package com.liuliangjie.cardgame

import com.badlogic.gdx.Game
import com.google.inject.Guice
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.module.SceneMudule
import com.liuliangjie.cardgame.ui.screen.MainMenuScreens

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : Game() {
    private lateinit var mainMenuScreens: MainMenuScreens
    private lateinit var configurationManager: ConfigurationManager

    override fun create() {
        // 创建依赖注入容器
        val injector = Guice.createInjector(SceneMudule())
        
        // 获取配置管理器
        configurationManager = injector.getInstance(ConfigurationManager::class.java)
        
        // 获取主菜单屏幕
        mainMenuScreens = injector.getInstance(MainMenuScreens::class.java)
        mainMenuScreens.setGame(this)
        setScreen(mainMenuScreens)
    }

}