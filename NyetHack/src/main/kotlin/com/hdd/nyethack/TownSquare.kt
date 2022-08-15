package com.hdd.nyethack

open class TownSquare : Room("The Town Square") {

    override val status: String = "Bustling"
    private var bellSound = "GWONG"

    fun ringBell() {
        narrate("The bell tower announces the hero's presence: $bellSound")
    }

    final override fun enterRoom() {
        narrate("The villagers rally and cheer as the hero enters")
        ringBell()
    }
}