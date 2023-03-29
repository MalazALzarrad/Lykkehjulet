package com.lykkehjulet.utils

class CharUtils {

    fun generateRandomChar(): List<Char> {

        // Add Random character
        val range: CharRange = 'A'..'Z'

        return range.shuffled().toList()
    }


}