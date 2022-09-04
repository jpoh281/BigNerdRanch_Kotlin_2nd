fun main(){
    val adversary = Jhava()
    println(adversary.utterGreeting())

    val friendshipLevel = adversary.determineFriendshipLevel()
    println(friendshipLevel?.lowercase())

    val adversaryHitPoints: Int = adversary.hitPoints
    println(adversaryHitPoints.coerceAtMost(100))
    println(adversaryHitPoints.javaClass)
}

class Hero {
}