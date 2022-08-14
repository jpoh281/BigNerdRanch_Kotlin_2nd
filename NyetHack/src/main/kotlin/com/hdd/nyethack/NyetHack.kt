package com.hdd.nyethack

lateinit var player: Player

fun main() {
//    com.hdd.nyethack.narrate("A hero enters the town of Kronstadt. What is their name?") { message -> "\u001b[33;1m$message\u001b[0m" }
//
//    val heroName = readLine()
//    require(heroName != null && heroName.isNotEmpty()) {
//        "The hero must have a name."
//    }
//
//    com.hdd.nyethack.changeNarratorMood()
    narrate("Welcome to NyetHack!")

    val playerName = promptHeroName()
    player = Player(playerName)
    player.prophesize()

    val mortality = if(player.isImmortal) "an immortal" else "a mortal"
    narrate("${player.name} of ${player.hometown} is ${player.title} heads to the town square")
    narrate("${player.name}, $mortality, has ${player.healthPoints} health points")

    visitTavern()
    player.castFireball()
    player.prophesize()
}

private fun promptHeroName(): String {
    narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
        "\u001b[33;1m$message\u001b[0m"
    }

//    val input = readLine()
//    require(input != null && input.isNotEmpty()){
//        "The hero must have a name"
//    }
//    return input

    println("Madrigal")
    return "Madrigal"
}