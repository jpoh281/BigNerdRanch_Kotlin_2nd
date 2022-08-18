package com.hdd.nyethack

import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.exitProcess

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
//    player.prophesize()


//    player.castFireball()
//    player.prophesize()

    Game.play();
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

object Game {
    private var currentRoom: Room = TownSquare()
    private var currentPosition = Coordinate(0, 0)

    private val worldMap = listOf(
        listOf(TownSquare(), Tavern(), Room("Back Room")),
        listOf(
            MonsterRoom("A Long Corridor"),
            Room("A Generic Room"),
            MonsterRoom("The Dungeon", generateRandomMonster(true))
        ),
        listOf(
            MonsterRoom("The Dungeon", generateRandomMonster(false)),
            MonsterRoom("The Dungeon", generateRandomMonster(false)),
            MonsterRoom("The Dungeon", generateRandomMonster(true))
        ),
        listOf(
            MonsterRoom("The Dungeon", monster = generateRandomMonster(true)),
            MonsterRoom("The Dungeon", generateRandomMonster(true))
        )
    )

    private fun generateRandomMonster(farAway: Boolean): Monster {
        val int = Random.nextInt(20)
        return when {
            int > 17 -> {
                if (farAway) {
                    Dragon()
                } else {
                    Naga()
                }
            }
            int % 2 == 1 -> {
                Skeleton()
            }
            else -> Goblin()
        }
    }

    init {
        narrate("Welcome, adventure")
        val mortality = if (player.isImmortal) "an immortal" else "a mortal"
        narrate("${player.name}, $mortality, has ${player.healthPoints} health points")
    }

    fun play() {
        while (true) {
            // Play NyetHack
            narrate("${player.name} of ${player.hometown} is ${player.title} is in ${currentRoom.description()}")
            currentRoom.enterRoom()

            print("> Enter your command: ")
            GameInput(readLine()).processCommand()
        }
    }

    fun move(direction: Direction) {
        val newPosition = direction.updateCoordinate(currentPosition)
        val newRoom = worldMap.getOrNull(newPosition.y)?.get(newPosition.x)

        if (newRoom != null) {
            narrate("The hero moves ${direction.name}")
            currentPosition = newPosition
            currentRoom = newRoom
        } else {
            narrate("You cannot move ${direction.name}")
        }
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        fun processCommand() = when (command.lowercase()) {
            "move" -> {
                val direction = Direction.values().firstOrNull { it.name.equals(argument, ignoreCase = true) }
                if (direction != null) {
                    move(direction)
                } else {
                    narrate("I don't know what direction that is")
                }
            }
            "fight" -> fight()
            else -> narrate("I'm not sure what you're trying to do")
        }
    }

    fun fight() {
        val monsterRoom = currentRoom as? MonsterRoom
        val currentMonster = monsterRoom?.monster
        if (currentMonster == null) {
            narrate("There's nothing to fight here")
            return
        }

        while (player.healthPoints > 0 && currentMonster.healthPoints > 0) {
            player.attack(currentMonster)
            if (currentMonster.healthPoints > 0) {
                currentMonster.attack(player)
            }
            Thread.sleep(1000)
        }

        if (player.healthPoints <= 0) {
            narrate("You have been defeated! Thanks for playing")
            exitProcess(0)
        } else {
            narrate("${currentMonster.name} has been defeated")
            monsterRoom.monster = null
        }
    }
}