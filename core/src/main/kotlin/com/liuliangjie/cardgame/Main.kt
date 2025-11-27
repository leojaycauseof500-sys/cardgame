package com.liuliangjie.cardgame

import com.badlogic.gdx.Game
import com.liuliangjie.cardgame.screens.MainMenuScreens

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : Game() {
    private lateinit var mainMenuScreens: MainMenuScreens

    override fun create() {
        mainMenuScreens = MainMenuScreens(this)
        setScreen(mainMenuScreens)
    }

}
