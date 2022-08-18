package com.hdd.nyethack

open class Room(val name: String) {

    protected open val status = "Calm"

    open fun description() = "$name (Currently: $status)"

    open fun enterRoom() {
        narrate("There is nothing to do here")
    }
}

open class MonsterRoom(name: String, var monster: Monster? = Goblin()) : Room(name) {

    override fun description() = super.description() + ("Creature: ${monster?.description ?: "NONE"}")

    override fun enterRoom() {
        if(monster == null){
            super.enterRoom()
        } else {
            narrate("Danger is lurking in this room")
        }
    }
}