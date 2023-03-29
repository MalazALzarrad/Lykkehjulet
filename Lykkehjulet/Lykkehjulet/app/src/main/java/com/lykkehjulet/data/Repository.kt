package com.lykkehjulet.data

import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun insertPuzzle(post: Puzzle) : Long

    suspend fun deletePuzzle(post: Puzzle)

     fun getCount() : Int

    suspend fun getAllPuzzle() : List<Puzzle>

     fun getAllPuzzleFlow() : Flow<List<Puzzle>>

}