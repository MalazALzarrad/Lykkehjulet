package com.lykkehjulet.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface PuzzleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Puzzle: Puzzle) : Long

    @Delete
    suspend fun delete(Puzzle: Puzzle)

    @Update()
    suspend fun update(Puzzle: Puzzle)

    @Query("SELECT * FROM Puzzle ORDER BY LOWER(id) Desc")
    fun getPuzzle(): List<Puzzle>

    @Query("SELECT COUNT(id) FROM Puzzle")
    fun getCount(): Int

    @Query("SELECT * FROM Puzzle ORDER BY LOWER(id) Desc")
    fun getPuzzleFlow(): Flow<List<Puzzle>>
}