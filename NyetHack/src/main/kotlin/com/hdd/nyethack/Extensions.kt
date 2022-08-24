package com.hdd.nyethack

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) = this + "!".repeat(enthusiasmLevel)

fun <T>T.print() : T {
    println(this)
    return this
}