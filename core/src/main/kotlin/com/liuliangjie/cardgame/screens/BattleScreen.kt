package com.liuliangjie.cardgame.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.liuliangjie.cardgame.battle.BattleEntity
import com.liuliangjie.cardgame.battle.Hand

class BattleScreen(private val game: com.badlogic.gdx.Game) : Screen {
    private lateinit var stage: Stage
    private val player = BattleEntity("Player", 20)
    private val enemy = BattleEntity("Enemy", 15)
    private val hand = Hand.starterHand()

    private lateinit var playerHpLabel: Label
    private lateinit var enemyHpLabel: Label

    override fun show() {
        stage = Stage()
        Gdx.input.inputProcessor = stage

        val table = Table()
        table.setFillParent(true)
        stage.addActor(table)

        val skin = Skin(Gdx.files.internal("ui/uiskin.json"))

        playerHpLabel = Label("Player HP: ${player.hp}", skin)
        enemyHpLabel = Label("Enemy HP: ${enemy.hp}", skin)

        // Top: HP info
        table.top().padTop(20f)
        table.add(playerHpLabel).padRight(40f)
        table.add(enemyHpLabel)
        table.row()

        // Center: simple message or spacing
        table.add().expandY().row()

        // Bottom: hand of cards
        val handTable = Table()
        for ((index, card) in hand.cards.withIndex()) {
            val btn = TextButton("${card.name} (${if (card.damage >= 0) "+${card.damage}" else card.damage})", skin)
            btn.addListener(object : ChangeListener() {
                override fun changed(event: com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent?, actor: com.badlogic.gdx.scenes.scene2d.Actor?) {
                    playCard(index, btn)
                }
            })
            handTable.add(btn).pad(10f)
        }

        table.add(handTable).expandX().bottom().padBottom(30f).colspan(2)
    }

    private fun playCard(index: Int, btn: TextButton) {
        if (index < 0 || index >= hand.cards.size) return
        val card = hand.cards[index]
        // Apply card effect: positive damage to enemy, negative -> heal player
        if (card.damage >= 0) {
            enemy.hp -= card.damage
        } else {
            player.hp -= card.damage // subtracting negative heals
        }

        // Disable the used button
        btn.isDisabled = true
        btn.setText("Used")

        updateHpLabels()

        // Simple enemy reaction: if still alive, deal fixed damage to player
        if (enemy.hp > 0) {
            val enemyDamage = 2
            player.hp -= enemyDamage
        }

        updateHpLabels()
    }

    private fun updateHpLabels() {
        playerHpLabel.setText("Player HP: ${player.hp}")
        enemyHpLabel.setText("Enemy HP: ${enemy.hp}")
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1f)
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
