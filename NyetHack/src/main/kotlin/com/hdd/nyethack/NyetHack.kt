package com.hdd.nyethack

val player: Player = Player()

fun main() {
//    com.hdd.nyethack.narrate("A hero enters the town of Kronstadt. What is their name?") { message -> "\u001b[33;1m$message\u001b[0m" }
//
//    val heroName = readLine()
//    require(heroName != null && heroName.isNotEmpty()) {
//        "The hero must have a name."
//    }
//
//    com.hdd.nyethack.changeNarratorMood()
    narrate("${player.name} is ${player.title}")
    player.changeName("Aurelia")
    narrate("${player.name} ${player.title}, heads to the town square")
    visitTavern()

    player.castFireball()
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