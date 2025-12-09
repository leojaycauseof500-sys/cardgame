package com.liuliangjie.cardgame.i18n.impl

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.I18NBundle
import com.google.inject.Inject
import com.liuliangjie.cardgame.configuration.ConfigurationManager

class UII18NServiceImpl @Inject constructor(
    configurationManager: ConfigurationManager,
) : AbstractI18NService(configurationManager) {
    private lateinit var bundle : I18NBundle

    init {
        loadBundle()
    }
    override fun get(key: String): String = bundle[key]

    private fun loadBundle() {
        bundle = I18NBundle.createBundle(Gdx.files.internal("assets/i18n/ui"), locale)
    }
}