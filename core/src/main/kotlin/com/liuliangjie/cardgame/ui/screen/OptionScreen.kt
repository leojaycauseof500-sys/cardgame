package com.liuliangjie.cardgame.ui.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.google.inject.Inject
import com.google.inject.name.Named
import com.liuliangjie.cardgame.Main
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.configuration.configuration.LanguageConfiguration
import com.liuliangjie.cardgame.configuration.configuration.UIConfiguration
import com.liuliangjie.cardgame.enums.LanguageEnum
import com.liuliangjie.cardgame.i18n.I18NService
import com.liuliangjie.cardgame.util.asset.KAssetManage

class OptionScreen @Inject constructor(
    private val game: ScreenSwitcher,
    private val configurationManager: ConfigurationManager,
    private val assetManager: KAssetManage,
    @param:Named("UI") private val i18NService: I18NService,
) : Screen {
    
    private lateinit var stage: Stage
    private lateinit var skin: Skin

    // UI Settings elements
    private lateinit var resolutionSelectBox: SelectBox<String>
    
    // Language Settings elements
    private lateinit var languageSelectBox: SelectBox<String>
    
    override fun show() {
        stage = Stage(ScreenViewport())
        Gdx.input.inputProcessor = stage
        
        // Create main container
        val mainTable = Table()
        mainTable.setFillParent(true)
        stage.addActor(mainTable)
        
        val exitButtonUpTexture = assetManager.getAsset<Texture>("ExitButton_up")
        val exitButtonOverTexture = assetManager.getAsset<Texture>("ExitButton_over")

        val font = assetManager.getAsset<BitmapFont>("textFont")
        val buttonStyle = TextButton.TextButtonStyle(
            TextureRegionDrawable(exitButtonUpTexture),
            null,
            null,
            font
        )

        buttonStyle.over = TextureRegionDrawable(exitButtonOverTexture)
        val exitButton = TextButton(
            "exit",
            buttonStyle
        )

        exitButton.addListener(object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent?, actor: Actor?) {
                game.switchTo(MainMenuScreens::class.java)
            }
        })

        mainTable.add(exitButton).align(Align.topRight).pad(10f).row()
    }
    
    private fun createTabbedPane() {

    }
    
    private fun createUISettingsPanel(container: Table) {
        val table = Table()
        table.align(Align.topLeft)
        
        // Resolution selector
        val resolutionLabel = Label(i18NService.get("options.resolution"), skin)
        resolutionSelectBox = SelectBox(skin)
        resolutionSelectBox.setItems(
            "1280x720",
            "1366x768", 
            "1920x1080",
            "2560x1440",
            "3840x2160"
        )
        
        // Get current resolution from configuration
        val uiConfig = configurationManager.getConfiguration(UIConfiguration::class) as UIConfiguration
        // For now, we'll just populate with common resolutions
        
        table.add(resolutionLabel).pad(10f)
        table.add(resolutionSelectBox).pad(10f).row()
        
        container.add(table).expand().fill()
    }
    
    private fun createLanguageSettingsPanel(container: Table) {
        val table = Table()
        table.align(Align.topLeft)
        
        // Language selector
        val languageLabel = Label(i18NService.get("options.language"), skin)
        languageSelectBox = SelectBox(skin)
        languageSelectBox.setItems(
            "English",
            "简体中文"
        )
        
        // Get current language from configuration
        val langConfig = configurationManager.getConfiguration(LanguageConfiguration::class) as LanguageConfiguration
        val currentLang = when (langConfig.language) {
            LanguageEnum.ENGLISH -> "English"
            LanguageEnum.CHINESE -> "简体中文"
        }
        languageSelectBox.selected = currentLang
        
        table.add(languageLabel).pad(10f)
        table.add(languageSelectBox).pad(10f).row()
        
        container.add(table).expand().fill()
    }
    
    private fun saveSettings() {

    }
    
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        
        stage.act(delta)
        stage.draw()
    }
    
    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }
    
    override fun pause() {}
    
    override fun resume() {}
    
    override fun hide() {
        Gdx.input.inputProcessor = null
    }
    
    override fun dispose() {
        stage.dispose()
    }
}