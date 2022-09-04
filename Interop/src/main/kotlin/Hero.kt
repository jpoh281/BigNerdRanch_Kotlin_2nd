@file:JvmName("Hero")
fun main(){
    val adversary = Jhava()
    println(adversary.utterGreeting())

    val friendshipLevel = adversary.determineFriendshipLevel()
    println(friendshipLevel?.lowercase())

    val adversaryHitPoints: Int = adversary.hitPoints
    println(adversaryHitPoints.coerceAtMost(100))
    println(adversaryHitPoints.javaClass)

    adversary.greeting = "Hello, Hero."
    println(adversary.utterGreeting())

    adversary.offerFood()
}

fun makeProclamation() = "Greeting, beast!"

@JvmOverloads
fun handOverFood(leftHand: String = "berries", rightHand:String = "beef"){
    println("Mmmm... you hand over some delicious $leftHand and $rightHand")
}

class Spellbook {
    @JvmField
    val spells = listOf("Magic Ms. L", "Lay on Hans")

    companion object {
        @JvmField
        var maxSpellCount = 10

        @JvmStatic
        fun getSpellbookGreeting() = println("I am the Great Grimoire!")
    }
}