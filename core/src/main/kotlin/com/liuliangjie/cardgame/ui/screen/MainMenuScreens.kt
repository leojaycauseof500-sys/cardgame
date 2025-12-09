package com.liuliangjie.cardgame.ui.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable
import com.google.inject.Inject
import com.google.inject.Singleton
import com.google.inject.name.Named
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.configuration.configuration.UIConfiguration
import com.liuliangjie.cardgame.enums.ConfigurationEnum
import com.liuliangjie.cardgame.enums.UIAssetEnum
import com.liuliangjie.cardgame.i18n.I18NService
import com.liuliangjie.cardgame.util.asset.KAssetManage

@Singleton
public class MainMenuScreens @Inject constructor(
    private val configurationManager: ConfigurationManager,
    private val assetManager: KAssetManage,
    @param:Named("UI") private val i18NService: I18NService
) : Screen {
    private lateinit var stage : Stage
    private var playBtnTexture: Texture? = null
    private lateinit var game: Game
    private val uiConfiguration = configurationManager.getConfiguration(UIConfiguration::class) as UIConfiguration

    fun setGame(game: Game) {
        this.game = game
    }

    override fun show() {

        stage = Stage()

        Gdx.input.inputProcessor = stage // 重要：将输入处理器设置为stage

        // 创建一个表格来布局按钮，表格会填满整个屏幕
        val table = Table()
        table.setFillParent(true)

        stage.addActor(table)
        val font = assetManager.getAsset<BitmapFont>("textFont")



        val defaultNp = assetManager.getAsset<NinePatch>("DefaultButton")
        val startGame = TextButton(
            i18NService.get("mainMenu.startButton.text"),
            TextButton.TextButtonStyle(NinePatchDrawable(defaultNp), null, null, font)
        )

        val optionsButton = TextButton(
            i18NService.get("mainMenu.optionsButton.text"),
            TextButton.TextButtonStyle(NinePatchDrawable(defaultNp), null, null, font)
        )
        val exitButton = TextButton(
            i18NService.get("mainMenu.exitButton.text"),
            TextButton.TextButtonStyle(NinePatchDrawable(defaultNp), null, null, font)
        )
        // Exit 关闭应用（在桌面端有效）
        exitButton.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                Gdx.app.exit()
            }
        })

        table.add(startGame).width(200f).height(60f).padBottom(20f)
        table.row()
        table.add(optionsButton).width(200f).height(60f).padBottom(20f)
        table.row()
        table.add(exitButton).width(200f).height(60f)

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // 更新并绘制stage
        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    override fun dispose() {
        playBtnTexture?.dispose()
        stage.dispose()
    }
}