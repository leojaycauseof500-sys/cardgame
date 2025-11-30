package com.liuliangjie.cardgame.module

import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.google.inject.AbstractModule

class SceneMudule : AbstractModule() {
    override fun configure() {
        this.bind(Skin::class.java).to(Skin::class.java)
    }
}
