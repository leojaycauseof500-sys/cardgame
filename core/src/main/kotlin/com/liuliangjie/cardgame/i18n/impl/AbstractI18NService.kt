package com.liuliangjie.cardgame.i18n.impl

import com.google.inject.Inject
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.configuration.configuration.LanguageConfiguration
import com.liuliangjie.cardgame.i18n.I18NService
import com.liuliangjie.cardgame.util.asset.KAssetManage

abstract class AbstractI18NService @Inject  constructor(
    protected val configurationManager: ConfigurationManager,
) : I18NService {
    protected val languageConfiguration = configurationManager.getConfiguration(LanguageConfiguration::class) as LanguageConfiguration
    protected val locale = languageConfiguration.language.locale
}