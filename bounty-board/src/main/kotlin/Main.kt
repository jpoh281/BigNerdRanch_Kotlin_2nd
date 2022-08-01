package main.kotlin

const val HERO_NAME = "Madrigal"
var playerLevel: Int = 0
// var playerLevel : UInt = 5.toUInt()
// var playerLevel : UInt = 5u

fun main() {

    println("$HERO_NAME announces her presence to the world.")
    println("What level is $HERO_NAME?")

    playerLevel = readLine()?.toIntOrNull() ?: 0

    println("$HERO_NAME's level is $playerLevel.")

    readBountyBoard()

    println("Time passes...")
    println("$HERO_NAME returns from her quest")

    playerLevel += 1
    println(playerLevel)
    readBountyBoard()

    forgeItem(itemName = "gauntlet", encrustWithJewels = true, material = "bronze", quantity = 1)
//    shouldReturnAString()
}


private fun obtainQuest(
    playerLevel: Int,
    playerClass: String = "Paladin",
    hasBeFriendedBarbarians: Boolean = true,
    hasAngeredBarbarians: Boolean = false,
): String? = when (playerLevel) {
    1 -> "Meet Mr. Bubbles in the land of soft things."
    in 2..5 -> {
        val canTalkToBarbarians = !hasAngeredBarbarians && hasBeFriendedBarbarians || playerClass == "barbarian"

        if (canTalkToBarbarians) {
            "Convince the barbarians to call off their invasion."
        } else {
            "Save the town from the barbarian invasions."
        }
    }
    6 -> "Locate the enchanted sword."
    7 -> "Recover the long-lost artifact of createion."
    8 -> "Defeat Nogartse, bringer of death and eater of worlds."
    else -> null
}

private fun readBountyBoard() {
    val quest: String? = obtainQuest(playerLevel)

    val message: String = quest?.replace("Nogartse", "xxxxxxxx")
        ?.let { censoredQuest ->

            """
            |$HERO_NAME approaches the bounty board. It reads:
            |   "$censoredQuest"
            """.trimMargin()
        } ?: "$HERO_NAME approaches the bounty board, but it is blank."

    println(message)
}

fun forgeItem(
    itemName: String = "sword",
    material: String = "iron",
    encrustWithJewels: Boolean = false,
    quantity: Int = 1
) {

}

/**
 * Always throws [NotImplementedError] stating that operation is not implemented.
 */
public inline fun TODO(): Nothing = throw NotImplementedError()

fun shouldReturnAString(): String {
    TODO()
    println("This is unreachable")
}