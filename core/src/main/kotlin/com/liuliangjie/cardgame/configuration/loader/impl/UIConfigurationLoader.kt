package com.liuliangjie.cardgame.configuration.loader.impl

import com.liuliangjie.cardgame.configuration.loader.AbstractConfigurationLoader
import com.liuliangjie.cardgame.enum.ConfigurationEnum

class UIConfigurationLoader : AbstractConfigurationLoader() {
    override val name: String
        get() = ConfigurationEnum.UI.name

    override fun load(): Any {
        TODO("Not yet implemented")
    }
}
