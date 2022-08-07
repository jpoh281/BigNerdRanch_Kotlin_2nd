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
private val menuItemPrices = List(menuData.size) {index ->
    val (_, name, price) = menuData[index].split(",")
    name to price.toDouble()
}.toMap()

private val menuItemTypes = List(menuData.size) {index ->
    val (type, name, _) = menuData[index].split(",")
    name to type
}.toMap()


fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME")
    narrate("There are sevaral items for sale:")
    narrate(menuItems.joinToString())

//    val patrons = mutableListOf("Eli", "Mordoc", "Sophie")
    val patrons : MutableSet<String> = mutableSetOf()
    val patronGold = mutableMapOf(
        TAVERN_MONSTER to 86.00,
        heroName to 4.50
    )

    while (patrons.size < 5) {
        patrons += "${firstName.random()} ${lastName.random()}"
        val patronName = "${firstName.random()} ${lastName.random()}"
        patrons += patronName
        patronGold += patronName to 6.0
    }

    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())
    

    repeat(3){
        placeOrder(patrons.random(), menuItems.random(), patronGold)
    }
    displayPatronBalances(patronGold)

}

private fun placeOrder(patronName: String, menuItemName: String, patronGold : MutableMap<String,Double>) {
    val itemPrice = menuItemPrices.getValue(menuItemName)

    narrate("$patronName speaks with $TAVERN_MONSTER to place an order")

    if(itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
        val action = when(menuItemTypes[menuItemName]) {
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

private fun displayPatronBalances (patronGold: MutableMap<String, Double>){
    narrate("$heroName intuitively knows how much money each patron has")
    patronGold.forEach { (patron, balance) ->
        narrate("$patron has ${"%.2f".format(balance)} gold")
    }
}