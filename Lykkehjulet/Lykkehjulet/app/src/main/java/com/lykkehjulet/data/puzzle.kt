package com.lykkehjulet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Puzzle(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val value : String
)
