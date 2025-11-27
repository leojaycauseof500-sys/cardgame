package com.liuliangjie.cardgame.battle

class Hand(val cards: MutableList<Card>) {
    companion object {
        fun starterHand(): Hand {
            return Hand(mutableListOf(
                Card("Strike", 3),
                Card("Strike", 3),
                Card("Minor Heal", -2)
            ))
        }
    }
}
