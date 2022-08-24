package com.hdd.nyethack

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) = this + "!".repeat(enthusiasmLevel)

fun String.frame(paddingInt: Int, formatChar: String = "*") : String {
    val greeting = "$this!"
    val middle = formatChar
        .padEnd(paddingInt, ' ')
        .plus(greeting)
        .plus(formatChar.padStart(paddingInt, ' '))
    val end = (0 until middle.length).joinToString("") { formatChar }
    return "$end\n$middle\n$end"
}

val String.numVowels
    get() = count { it.lowercase() in "aeiou" }

fun <T> T.print(): T {
    println(this)
    return this
}

operator fun List<List<Room>>.get(coordinate: Coordinate) = getOrNull(coordinate.y)?.getOrNull(coordinate.x)

infix fun Coordinate.move(direction: Direction) = direction.updateCoordinate(this)

fun Room?.orEmptyRoom(name: String = "the middle of nowhere"): Room = this ?: Room(name)