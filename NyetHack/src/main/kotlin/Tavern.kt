import java.io.File

private const val TAVERN_MONSTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MONSTER's Folly"

private val firstName = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastName = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt").readText().split("\n")
private val menuItems = List(menuData.size) {index ->
    val (_, name, price) = menuData[index].split(",")
    name
}

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME")

    println("*** Welcome to $TAVERN_NAME ***")
    println(printSortedMenu())

//    narrate("There are sevaral items for sale:")
//    narrate(menuItems.joinToString())

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

private fun printSortedMenu() {

    var longestMenuLength = 0

    var tempMenuItems = List(menuData.size) {index ->
        val (_, name, price) = menuData[index].split(",")
        "$name$price"
    }

    longestMenuLength = tempMenuItems.maxOf {
        it.length + 5
    }

    var menuByCategory = menuData.groupBy{
        val (type, _, _) = it.split(",")
        type
    }

    menuByCategory.forEach{ (category, menus) ->
        println("\t\t~[$category]~")
        println(menus.joinToString("\n") {
            val (_, name, price) = it.split(",")
            "${name.padEnd(longestMenuLength - price.length, '.')}$price"
        })
    }

//    val menuItems = List(menuData.size) {index ->
//        val (_, name, price) = menuData[index].split(",")
//        "${name.padEnd( longestMenuLength - price.length, '.')}$price"
//    }
//
//    return menuItems
}