package com.hdd.nyethack

import java.io.File
import kotlin.random.Random
import kotlin.random.nextInt

private const val TAVERN_MONSTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MONSTER's Folly"

private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastNames = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt").readText().split("\n").map { it.split(",") }

private val menuItems = menuData.map { (_, name, _) -> name }

private val menuItemPrices = menuData.associate { (_, name, price) ->
    name to price.toDouble()
}

private val menuItemTypes = menuData.associate { (type, name) ->
    name to type
}

class Tavern : Room(TAVERN_NAME){
    override val status: String = "Busy"

    override val lootBox : LootBox<Key> = LootBox(Key("key to Nogartse's evil lair"))

    val patrons: MutableSet<String> = firstNames.shuffled().zip(lastNames.shuffled()) { firstName, lastName ->
        "$firstName $lastName"
    }.toMutableSet()


    val patronGold = mutableMapOf(
        TAVERN_MONSTER to 86.00,
        player.name to 4.50,
        *patrons.map { it to 6.00 }.toTypedArray()
    )

    val itemOfDay = patrons.flatMap { getFavoriteMenuItems(it) }.random()

    override fun enterRoom() {
        narrate("${player.name} enters $TAVERN_NAME")
        narrate("There are sevaral items for sale:")
        narrate(menuItems.joinToString())


        narrate("${player.name} sees several patrons in the tavern:")
        narrate(patrons.joinToString())


        narrate("The item of the day is $itemOfDay")

        placeOrder(patrons.random(), menuItems.random())
    }

    private fun placeOrder(patronName: String, menuItemName: String) {
        val itemPrice = menuItemPrices.getValue(menuItemName)

        narrate("$patronName speaks with $TAVERN_MONSTER to place an order")

        if (itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
            val action = when (menuItemTypes[menuItemName]) {
                "shandy", "elixir" -> "pours"
                "meal" -> "serves"
                else -> "hands"
            }

            narrate("$TAVERN_MONSTER $action $patronName a $menuItemName")
            narrate("$patronName pays $TAVERN_MONSTER $itemPrice gold")
            patronGold[patronName] = patronGold.getValue(patronName) - itemPrice
            patronGold[TAVERN_MONSTER] = patronGold.getValue(TAVERN_MONSTER) + itemPrice
        } else {
            narrate("$TAVERN_MONSTER says, \"You need mor coin for a $menuItemName\"")
        }
    }
}

private fun getFavoriteMenuItems(patron: String): List<String> {
    return when (patron) {
        "Alex Ironfoot" -> menuItems.filter { menuItem ->
            menuItemTypes[menuItem]?.contains("dessert") == true
        }
        else -> menuItems.shuffled().take(Random.nextInt(1..2))

    }
}
