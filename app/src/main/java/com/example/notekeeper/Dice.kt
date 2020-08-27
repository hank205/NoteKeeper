package com.example.notekeeper

class Dice(private val sides: Int) {
    fun roll(): Int {
        return (1..sides).random()
    }
}