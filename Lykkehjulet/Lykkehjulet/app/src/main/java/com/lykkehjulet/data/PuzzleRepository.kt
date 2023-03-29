package com.lykkehjulet.data

import kotlinx.coroutines.flow.Flow

class PuzzleRepository(var puzzleDao: PuzzleDao) : Repository {
    
    companion object {
        private const val TAG = "PuzzleRepository"
    }

    override suspend fun insertPuzzle(puzzle: Puzzle): Long {
      return  puzzleDao.insert(puzzle)
    }

    override suspend fun deletePuzzle(puzzle: Puzzle) {
        puzzleDao.delete(puzzle)
    }

    override fun getCount(): Int {
        return puzzleDao.getCount()
    }

    override suspend fun getAllPuzzle(): List<Puzzle> {
       return puzzleDao.getPuzzle()
    }

    override  fun getAllPuzzleFlow(): Flow<List<Puzzle>> {

        return puzzleDao.getPuzzleFlow()

    }


}