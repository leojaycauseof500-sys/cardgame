package com.liuliangjie.cardgame.ui.screen

import com.badlogic.gdx.Screen

interface ScreenSwitcher {
    fun switchTo(screen: Class<out Screen>)
}