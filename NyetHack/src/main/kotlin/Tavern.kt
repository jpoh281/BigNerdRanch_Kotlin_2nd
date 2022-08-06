import java.io.File

private const val TAVERN_MONSTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MONSTER's Folly"

private val firstName = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastName = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt").readText().split("\n")
private val menuItems = List(menuData.size) {index ->
    val (_, name, _) = menuData[index].split(",")
    name
}

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME")
    narrate("There are sevaral items for sale:")
    narrate(menuItems.joinToString())

//    val patrons = mutableListOf("Eli", "Mordoc", "Sophie")
    val patrons : MutableSet<String> = mutableSetOf()

    while (patrons.size < 10) {
        patrons += "${firstName.random()} ${lastName.random()}"
    }

    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    repeat(3) {
        placeOrder(patrons.random(), menuItems.random())
    }
}

private fun placeOrder(patronName: String, menuItemName: String) {
    narrate("$patronName speaks with $TAVERN_MONSTER to place an order")
    narrate("$TAVERN_MONSTER hands $patronName a $menuItemName")
}