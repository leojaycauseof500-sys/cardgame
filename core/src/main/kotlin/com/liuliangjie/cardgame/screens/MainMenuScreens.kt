package com.liuliangjie.cardgame.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

public class MainMenuScreens(private val game: Game) : Screen {
    private lateinit var stage : Stage
    private var playBtnTexture: Texture? = null

    override fun show() {
        stage = Stage()

        Gdx.input.inputProcessor = stage // 重要：将输入处理器设置为stage

        // 创建一个表格来布局按钮，表格会填满整个屏幕
        val table = Table()
        table.setFillParent(true)
        stage.addActor(table)

        // 获取默认的皮肤（按钮样式）
        val skin = Skin(Gdx.files.internal("ui/uiskin.json")) // 需要默认皮肤文件

        // 创建三个按钮
        // 这里我们用附加图片作为 Play 按钮的背景（运行时创建样式）
        val optionsButton = TextButton("Options", skin)
        val exitButton = TextButton("Exit", skin)

        // 加载图片：请把你的附件图片保存为 assets/ui/my-button.png
        try {
            val tex = Texture(Gdx.files.internal("ui/bottom/tile_0030.png"))
            playBtnTexture = tex
            val upDrawable = TextureRegionDrawable(TextureRegion(tex))
            val downDrawable = TextureRegionDrawable(TextureRegion(tex))
            val font = skin.getFont("default")
            val playStyle = TextButton.TextButtonStyle().apply {
                up = upDrawable
                down = downDrawable
                this.font = font
            }
            val playButton = TextButton("Play", playStyle)

            // Play 按钮跳转到战斗场景
            playButton.addListener(object : ChangeListener() {
                override fun changed(event: com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent?, actor: Actor?) {
                    game.screen = BattleScreen(game)
                }
            })

            // 将 playButton 添加到表格
            table.add(playButton).width(200f).height(60f).padBottom(20f)
            table.row() // 换行
        } catch (e: Exception) {
            // 如果图片不存在或加载失败，回退到默认皮肤按钮
            val playButton = TextButton("Play", skin)
            playButton.addListener(object : ChangeListener() {
                override fun changed(event: com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent?, actor: Actor?) {
                    game.screen = BattleScreen(game)
                }
            })
            table.add(playButton).width(200f).height(60f).padBottom(20f)
            table.row()
        }

        // Exit 关闭应用（在桌面端有效）
        exitButton.addListener(object : ChangeListener() {
            override fun changed(event: com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent?, actor: Actor?) {
                Gdx.app.exit()
            }
        })

        // 将按钮添加到表格中，每个按钮占一行
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