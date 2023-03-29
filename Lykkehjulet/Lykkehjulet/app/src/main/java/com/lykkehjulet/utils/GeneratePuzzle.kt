package com.lykkehjulet.utils

import com.lykkehjulet.data.PuzzleRepository
import com.lykkehjulet.screen.play.GameState

class GeneratePuzzle {

    companion object {
        private const val TAG = "GeneratePuzzle"
        private const val Default  = "Super Mario"
    }


   suspend fun create(puzzleRepository: PuzzleRepository) : GameState {

        val count = puzzleRepository.getCount()

      val category = if (count> 0) {
           puzzleRepository.getAllPuzzle().random().value.uppercase()
       } else {
          Default.uppercase()
       }

      return GameState(category = category , found =0)

    }



}