package com.liuliangjie.cardgame.ui.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.google.inject.Inject
import com.google.inject.Singleton
import com.liuliangjie.cardgame.configuration.ConfigurationManager
import com.liuliangjie.cardgame.configuration.configuration.UIConfiguration
import com.liuliangjie.cardgame.enums.ConfigurationEnum
import com.liuliangjie.cardgame.enums.UIAssetEnum
import com.liuliangjie.cardgame.ui.KAssetManage
import java.security.Principal

@Singleton
public class MainMenuScreens @Inject constructor(
    private val skin : Skin,
    private val configurationManager: ConfigurationManager,
    private val assetManager: KAssetManage
) : Screen {
    private lateinit var stage : Stage
    private var playBtnTexture: Texture? = null
    private lateinit var game: Game
    private val uiConfiguration = configurationManager.getConfiguration(ConfigurationEnum.UI.name) as UIConfiguration

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


        // 创建三个按钮
        // 这里我们用附加图片作为 Play 按钮的背景（运行时创建样式）
        val optionsButton = TextButton("Options", skin)
        val exitButton = TextButton("Exit", skin)

        val defaultNp = assetManager.getAsset<NinePatch>(UIAssetEnum.DefaultButtonNinePatch.assetName)
        val startGame = TextButton("Start", TextButton.TextButtonStyle(NinePatchDrawable(defaultNp), null, null))

        // Exit 关闭应用（在桌面端有效）
        exitButton.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                Gdx.app.exit()
            }
        })

        // 将按钮添加到表格中，每个按钮占一行
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