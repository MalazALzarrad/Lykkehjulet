package com.lykkehjulet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Puzzle::class], version = 1)

abstract  class PuzzleDatabase : RoomDatabase() {

    abstract val puzzleDao : PuzzleDao

    companion object {
        @Volatile
        private var INSTANCE: PuzzleDatabase? = null

        fun getInstance(context: Context): PuzzleDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    PuzzleDatabase::class.java,
                    "database_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }


}