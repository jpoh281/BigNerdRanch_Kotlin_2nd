package com.hdd.nyethack

import kotlin.random.Random

interface Fightable {
    val name: String
    var healthPoints: Int
    val diceCount: Int
    val diceSides: Int

    fun takeDamage(damage: Int)

    fun attack(opponent: Fightable) {
        val damageRoll = (0 until diceCount).sumOf {
            Random.nextInt(diceSides + 1)
        }
        narrate("$name deals $damageRoll to ${opponent.name}")
        opponent.takeDamage(damageRoll)
    }
}

abstract class Monster(override val name: String, val description: String, override var healthPoints: Int) : Fightable {
    override fun takeDamage(damage: Int) {
        healthPoints -= damage
    }
}

class Goblin(name: String = "Goblin", description: String = "A nasty-looking goblin", healthPoints: Int = 30) :
    Monster(name, description, healthPoints) {
    override val diceCount: Int = 2
    override val diceSides: Int = 8
}

class Dragon(name: String = "Dragon", description: String = "A Super Dragon", healthPoints: Int = 2000) :
    Monster(name, description, healthPoints) {
    override val diceCount: Int = 50
    override val diceSides: Int = 10
}

class Skeleton(name: String = "Skeleton", description: String = "A white Skeleton", healthPoints: Int = 70) :
    Monster(name, description, healthPoints) {
    override val diceCount: Int = 5
    override val diceSides: Int = 6
}

class Naga(name: String = "Naga", description: String = "A Wet Naga", healthPoints: Int = 100) :
    Monster(name, description, healthPoints) {
    override val diceCount: Int = 3
    override val diceSides: Int = 4
}