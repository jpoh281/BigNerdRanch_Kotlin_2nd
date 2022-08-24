package com.hdd.nyethack

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) = this + "!".repeat(enthusiasmLevel)

fun Any.print() : Any {
    println(this)
    return this
}